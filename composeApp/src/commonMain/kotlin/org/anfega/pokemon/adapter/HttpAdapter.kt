package org.anfega.pokemon.adapter

import io.ktor.client.HttpClient
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.encodeToString
import org.anfega.pokemon.domain.common.FetchRequest
import org.anfega.pokemon.domain.common.NetworkResponse
import org.anfega.pokemon.domain.common.Response
import org.anfega.pokemon.exception.PokemonException
import org.anfega.pokemon.repository.common.FormDatable
import org.anfega.pokemon.repository.common.Jsonable
import org.anfega.pokemon.utils.getParser

open class HttpAdapter (
    val client: HttpClient
){

    private var defaultHeaders = mutableMapOf(
        "Content-Type" to "application/json"
    )

    suspend fun save(request: FetchRequest, body: FormDatable): NetworkResponse {
        return executeRequest {
            client.submitFormWithBinaryData(
                url = "${request.server}${request.endpoint}",
                formData = body.handle()
            ) {
                defaultHeaders.forEach { (key, value) -> header(key, value) }
            }
        }
    }

    suspend fun get(request: FetchRequest): NetworkResponse {
        return executeRequest {
            client.get("${request.server}${request.endpoint}") {
                defaultHeaders.forEach { (key, value) -> header(key, value) }
            }
        }
    }

    suspend fun save(request: FetchRequest, body: Jsonable): NetworkResponse {
        return executeRequest {
            when (request.method) {
                HttpMethod.POST.toString() -> client.post("${request.server}${request.endpoint}") {
                    defaultHeaders.forEach { (key, value) -> header(key, value) }
                    setBody(body.handle())
                }

                HttpMethod.PUT.toString() -> client.put("${request.server}${request.endpoint}") {
                    defaultHeaders.forEach { (key, value) -> header(key, value) }
                    setBody(body.handle())
                }

                else -> {
                    throw IllegalArgumentException("Unsupported HTTP method: ${request.method}")
                }
            }
        }
    }


    private suspend fun executeRequest(
        block: suspend () -> HttpResponse
    ): NetworkResponse {
        return try {
            val blockResponse = block()
            val response = getParser().decodeFromString<Response>(blockResponse.bodyAsText())

            if (blockResponse.status == HttpStatusCode.OK
                || blockResponse.status == HttpStatusCode.Created
                || blockResponse.status == HttpStatusCode.Accepted
            ) {
                NetworkResponse(
                    success = true,
                    message = "Items Obtenidos exitosamente",
                    body = getParser().encodeToString(response.results)
                )
            } else {
                NetworkResponse(
                    success = false,
                    message = "Error al obtener los items",
                    body = null,
                    error = PokemonException("Error al obtener los items")
                )
            }
        } catch (e: Exception) {
            NetworkResponse(
                success = false,
                message = e.message ?: "Unknown error",
                body = null,
                error = e
            )
        }
    }

    enum class HttpMethod {
        POST, PUT
    }
}