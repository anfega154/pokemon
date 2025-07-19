package org.anfega.pokemon.domain.common

data class FetchRequest(
    val endpoint: String,
    val method : String = "POST",
    val server : String
)
