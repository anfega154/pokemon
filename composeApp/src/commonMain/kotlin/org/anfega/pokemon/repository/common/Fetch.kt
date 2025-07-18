package org.anfega.pokemon.repository.common

import org.anfega.pokemon.adapter.HttpAdapter
import org.anfega.pokemon.domain.common.FetchRequest
import org.anfega.pokemon.domain.common.NetworkResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class Fetch : KoinComponent {
    private val httpAdapter: HttpAdapter by inject()

    suspend fun save(requestData: FetchRequest, body: FormDatable): NetworkResponse {
        return httpAdapter.save(requestData, body)
    }

    suspend fun get(requestData: FetchRequest): NetworkResponse {
        return httpAdapter.get(requestData)
    }

    suspend fun save(requestData: FetchRequest, body: Jsonable): NetworkResponse {
        return httpAdapter.save(requestData, body)
    }

}