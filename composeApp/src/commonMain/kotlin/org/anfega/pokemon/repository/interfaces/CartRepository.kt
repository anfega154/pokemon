package org.anfega.pokemon.repository.interfaces

import org.anfega.pokemon.domain.model.Pokemon

interface CartRepository {
    suspend fun getCartItems(): List<Pokemon>
    suspend fun addToCart(pokemon: Pokemon)
    suspend fun removeFromCart(pokemonId: Int)
    suspend fun clearCart()
}