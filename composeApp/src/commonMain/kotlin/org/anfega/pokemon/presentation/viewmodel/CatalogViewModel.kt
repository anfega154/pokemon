package org.anfega.pokemon.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.anfega.pokemon.domain.model.Pokemon
import org.anfega.pokemon.repository.CartRepositoryImpl
import org.anfega.pokemon.repository.CatalogRepository
import org.anfega.pokemon.repository.common.PokemonViewModel

class CatalogViewModel : PokemonViewModel<Pokemon>() {
    private val catalogRepository = CatalogRepository(getRepository())
    private val cartRepository = CartRepositoryImpl(getRepository())

    var pokemonList by mutableStateOf<List<Pokemon>>(emptyList())
    var isLoading by mutableStateOf(false)
    private var currentOffset = 0

    var cartCount by mutableStateOf(0)
        private set

    init {
        updateCartCount()
    }

    suspend fun loadInitial() {
        if (pokemonList.isEmpty()) {
            loadMore()
        }
    }

    suspend fun loadMore() {
        if (isLoading) return
        isLoading = true
        val newPage = catalogRepository.getPokemonPage(currentOffset, 20)
        pokemonList = pokemonList + newPage
        currentOffset += 20
        isLoading = false
    }

    suspend fun addToCart(pokemon: Pokemon) {
        cartRepository.addToCart(pokemon)
        updateCartCount()
        getUtils().vibrate()
    }

    private fun updateCartCount() {
        cartCount = cartRepository.getCartItemsSync().size
    }
}
