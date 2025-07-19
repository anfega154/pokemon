package org.anfega.pokemon

import androidx.compose.ui.window.ComposeUIViewController
import com.database.Database
import org.anfega.pokemon.adapter.DatabaseDriverFactory
import org.anfega.pokemon.adapter.appModule
import org.anfega.pokemon.utils.PokemonUtils
import org.koin.core.context.startKoin

fun MainViewController() = ComposeUIViewController { App() }

fun initKoin() {
    val pokemonUtils = PokemonUtils()
    startKoin{
        modules(appModule(Database.invoke(DatabaseDriverFactory().createDriver()),pokemonUtils))
    }.koin
}