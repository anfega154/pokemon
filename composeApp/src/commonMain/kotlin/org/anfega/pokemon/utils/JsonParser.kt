package org.anfega.pokemon.utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.ExperimentalSerializationApi


@OptIn(ExperimentalSerializationApi::class)
fun getParser(): Json {
    return Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }
}