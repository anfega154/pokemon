package org.anfega.pokemon.repository.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class PokemonViewModel<T : Any> : KoinComponent, ViewModel() {

    private val pokemonRepository by inject<PokemonRepository<T>>()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    protected fun handleError(error: Throwable) {
        _errorMessage.value = error.message
    }

    protected fun clearError() {
        _errorMessage.value = null
    }

    protected fun getRepository() = pokemonRepository

    protected fun getDatabase() = pokemonRepository.getDatabase()
}