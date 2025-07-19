package org.anfega.pokemon.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonItem(
    val name: String,
    val url: String
)
