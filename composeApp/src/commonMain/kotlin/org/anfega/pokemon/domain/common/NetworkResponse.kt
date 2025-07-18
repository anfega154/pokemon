package org.anfega.pokemon.domain.common
import io.ktor.http.Headers
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse(
    val success: Boolean? = null,
    val message: String? = null,
    val body: String? = null,
    val error: @Contextual Exception? = null,
    val headers: Headers? = null
)