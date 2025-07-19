package org.anfega.pokemon.repository.common

import com.database.Database
import org.anfega.pokemon.domain.common.FetchRequest
import org.anfega.pokemon.domain.common.NetworkResponse
import org.anfega.pokemon.domain.common.ParamsRepository
import org.anfega.pokemon.exception.PokemonException
import org.anfega.pokemon.utils.PokemonUtils
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class PokemonRepository<T : Any>(
    private val database: Database
) : KoinComponent {
    private val pokemonNetwork: PokemonUtils by inject()
    private val request = Fetch()
    fun getDatabase() = database

    suspend fun push(paramsRepository: ParamsRepository<T>, body: FormDatable): NetworkResponse {
        return executeSafely {
            val requestData = createFetchRequest(paramsRepository)
            val response = request.save(requestData, body)
            paramsRepository.entity?.let { paramsRepository.query(it) }
            response
        }
    }

    suspend fun push(paramsRepository: ParamsRepository<T>, body: Jsonable): NetworkResponse {
        return executeSafely {
            val requestData = createFetchRequest(paramsRepository)
            val response = request.save(requestData, body)
            paramsRepository.entity?.let { paramsRepository.query(it) }
            response
        }
    }

    suspend fun get(paramsRepository: ParamsRepository<T>): String? {
        return executeSafely {
            val requestData = createFetchRequest(paramsRepository)
            val networkResponse = request.get(requestData)
            networkResponse.body
        }
    }

    private fun createFetchRequest(paramsRepository: ParamsRepository<T>): FetchRequest {
        return FetchRequest(
            endpoint = paramsRepository.endpoint,
            method = paramsRepository.method,
            server = paramsRepository.server,
        )
    }

    private suspend fun <R> executeSafely(action: suspend () -> R): R {
        return if (pokemonNetwork.isConnectedOrConnecting()) {
            action()
        } else {
            throw PokemonException(
                "No internet connection. Please check your network settings."
            )
        }
    }
}