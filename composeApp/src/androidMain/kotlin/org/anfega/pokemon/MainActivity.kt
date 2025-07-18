package org.anfega.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.database.Database
import org.anfega.pokemon.adapter.DatabaseDriverFactory
import org.anfega.pokemon.adapter.appModule
import org.anfega.pokemon.utils.PokemonNetwork
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val pokemonNetwork = PokemonNetwork(this@MainActivity)

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule(Database.invoke(DatabaseDriverFactory(this@MainActivity).createDriver()), pokemonNetwork))
        }


        setContent {
            App()
        }
    }
}