package org.anfega.pokemon.repository

import org.anfega.pokemon.domain.common.ParamsRepository
import org.anfega.pokemon.domain.model.Pokemon
import org.anfega.pokemon.domain.model.PokemonItem
import org.anfega.pokemon.exception.PokemonException
import org.anfega.pokemon.repository.common.PokemonRepository
import org.anfega.pokemon.utils.Constants
import org.anfega.pokemon.utils.getParser

class CatalogRepository(
    pokemonRepository: PokemonRepository<Pokemon>
) : PokemonRepository<Pokemon>(
    pokemonRepository.getDatabase()
) {
    private val database = pokemonRepository.getDatabase()

    suspend fun getPokemonPage(offset: Int, limit: Int): List<Pokemon> {
        try {
            val paramsRepository = ParamsRepository<Pokemon>(
                entity = null,
                query = {},
                endpoint = "/pokemon?offset=$offset&limit=$limit",
                method = "GET",
                server = "https://pokeapi.co/api/v2"
            )

            val response = get(paramsRepository)

            return when {
                response.success != true -> emptyList()
                response.body.isNullOrBlank() -> emptyList()
                else -> {
                    val pokemonItems =
                        getParser().decodeFromString<List<PokemonItem>>(response.body)
                    savePokemonToDatabase(pokemonItems)
                    pokemonItems.toPokemonList()
                }
            }
        } catch (e: Exception) {
            if (e is PokemonException &&
                e.message == Constants.NO_CONNECTION_MESSAGE
            ) {
                val offlineItems = getPokemons()
                return offlineItems.toPokemonList()
            }
            throw PokemonException(e.message, e)
        }
    }

    private fun List<PokemonItem>.toPokemonList(): List<Pokemon> {
        try {
            return this.mapNotNull { item ->
                val id = item.url.trimEnd('/').split("/").lastOrNull()?.toIntOrNull()
                    ?: return@mapNotNull null
                Pokemon(
                    id = id,
                    name = item.name,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
                    price = (5..20).random().toDouble()
                )
            }
        } catch (e: Exception) {
            throw PokemonException("Failed to convert PokemonItem to Pokemon: ${e.message}", e)
        }

    }


    private fun savePokemonToDatabase(pokemons: List<PokemonItem>) {
        try {
            pokemons.forEach { pokemon ->
                database.pokemonQueries.insertPokemon(
                    name = pokemon.name,
                    url = pokemon.url
                )
            }
        } catch (e: Exception) {
            throw PokemonException("Failed to save pokemons to database: ${e.message}", e)
        }

    }

    private fun getPokemons(): List<PokemonItem> {
        return database.pokemonQueries.selectAll().executeAsList().map {
            PokemonItem(it.name, it.url ?: "")
        }
    }
}