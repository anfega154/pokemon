package org.anfega.pokemon.repository

import org.anfega.pokemon.domain.common.ParamsRepository
import org.anfega.pokemon.domain.model.Pokemon
import org.anfega.pokemon.domain.model.PokemonItem
import org.anfega.pokemon.exception.PokemonException
import org.anfega.pokemon.repository.common.PokemonRepository
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
                endpoint = "/pokemon?offset=${offset}&limit=${limit}",
                method = "GET",
                server = "https://pokeapi.co/api/v2",
            )
            val response = get(paramsRepository)
            if (response.isNullOrBlank()) return emptyList()
            return getParser().decodeFromString<List<PokemonItem>>(response)
                .mapNotNull { item ->
                    val id = item.url.trimEnd('/').split("/").lastOrNull()?.toIntOrNull()
                        ?: return@mapNotNull null
                    Pokemon(
                        id = id,
                        name = item.name,
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png",
                        price = (5..20).random().toDouble()
                    )
                }
        } catch (e: Exception) {
            throw PokemonException(e.message, e)
        }
    }


}