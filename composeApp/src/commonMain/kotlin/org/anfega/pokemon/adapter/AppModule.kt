package org.anfega.pokemon.adapter

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

fun appModule() = module {
    single<HttpClient> { HttpClient{install(ContentNegotiation) {json()} } }
    single { HttpAdapter(get()) }
}