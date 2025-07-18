package org.anfega.pokemon.adapter

import com.database.Database
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.anfega.pokemon.repository.common.PokemonRepository
import org.anfega.pokemon.utils.PokemonNetwork
import org.koin.dsl.module

fun appModule(database: Database, pokemonNetwork: PokemonNetwork) = module {
    single { pokemonNetwork }
    single<HttpClient> { HttpClient{install(ContentNegotiation) {json()} } }
    single { HttpAdapter(get()) }
    single { PokemonRepository<Any>(database) }
}