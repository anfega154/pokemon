package org.anfega.pokemon.domain.common
import kotlinx.serialization.Serializable
import org.anfega.pokemon.domain.model.PokemonItem

@Serializable
data class Response(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonItem>
)
