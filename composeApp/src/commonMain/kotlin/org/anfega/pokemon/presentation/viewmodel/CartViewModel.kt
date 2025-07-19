package org.anfega.pokemon.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.anfega.pokemon.domain.model.Pokemon
import org.anfega.pokemon.repository.CartRepositoryImpl
import org.anfega.pokemon.repository.common.PokemonViewModel

class CartViewModel : PokemonViewModel<Pokemon>(){

    var cartItems by mutableStateOf<List<Pokemon>>(emptyList())

    private val cartRepository = CartRepositoryImpl(getRepository())

    suspend fun loadCart() {
        cartItems = cartRepository.getCartItems()
    }

    suspend fun removeItem(pokemonId: Int) {
        cartRepository.removeFromCart(pokemonId)
        getUtils().vibrate()
        loadCart()
    }
}