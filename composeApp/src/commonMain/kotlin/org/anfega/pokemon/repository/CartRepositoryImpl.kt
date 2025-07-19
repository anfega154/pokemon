package org.anfega.pokemon.repository

import org.anfega.pokemon.domain.model.Pokemon
import org.anfega.pokemon.repository.common.PokemonRepository
import org.anfega.pokemon.repository.interfaces.CartRepository

class CartRepositoryImpl(
    pokemonRepository: PokemonRepository<Pokemon>
) : CartRepository, PokemonRepository<Pokemon>(
    pokemonRepository.getDatabase()
) {
    private val queries = pokemonRepository.getDatabase().pokemonEntityQueries

    override suspend fun getCartItems(): List<Pokemon> =
        queries.selectAll().executeAsList().map {
            Pokemon(it.id.toInt(), it.name, it.imageUrl, it.price)
        }

    fun getCartItemsSync(): List<Pokemon> =
        queries.selectAll().executeAsList().map {
            Pokemon(it.id.toInt(), it.name, it.imageUrl, it.price)
        }

    override suspend fun addToCart(pokemon: Pokemon) =
        queries.insertOrReplace(
            id = pokemon.id.toLong(),
            name = pokemon.name,
            imageUrl = pokemon.imageUrl,
            price = pokemon.price
        )

    override suspend fun removeFromCart(pokemonId: Int) =
        queries.deleteById(pokemonId.toLong())

    override suspend fun clearCart() = queries.deleteAll()
}
