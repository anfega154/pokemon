package org.anfega.pokemon.repository.common


import io.ktor.http.content.PartData

interface FormDatable {
    fun handle(): List<PartData>
}