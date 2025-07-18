package org.anfega.pokemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import org.anfega.pokemon.ui.pages.HomeScreen
import org.anfega.pokemon.utils.Routes


@Composable
fun Navigate(navigator: Navigator) {
    val initialRoute = Routes.HOME

    LaunchedEffect(initialRoute) {
        navigator.navigate(initialRoute)
    }

    NavHost(
        navigator = navigator, initialRoute = initialRoute
    ) {
        scene(route = Routes.HOME) {
            HomeScreen(navigator)
        }

        scene(route = Routes.CAR) {
        }

    }
}