package org.anfega.pokemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.database.Database
import org.anfega.pokemon.adapter.DatabaseDriverFactory
import org.anfega.pokemon.adapter.appModule
import org.anfega.pokemon.utils.PokemonUtils
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {

    object ContextHolder {
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ContextHolder.context = this

        val pokemonUtils = PokemonUtils(this@MainActivity)

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule(Database.invoke(DatabaseDriverFactory(this@MainActivity).createDriver()), pokemonUtils))
        }


        setContent {
            App()
        }
    }
}