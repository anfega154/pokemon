package org.anfega.pokemon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.rememberNavigator
import org.anfega.pokemon.ui.navigation.Navigate
import org.anfega.pokemon.ui.themes.AppTheme

@Composable
@Preview
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()

        AppTheme {
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                Navigate(navigator)
            }
        }
    }
}