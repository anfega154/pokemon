package org.anfega.pokemon.domain.common

data class ParamsRepository<T : Any>(
    val entity: T? = null,
    val query: (T) -> Unit? = {},
    val endpoint: String,
    val method: String = "POST",
    val server: String,
)
