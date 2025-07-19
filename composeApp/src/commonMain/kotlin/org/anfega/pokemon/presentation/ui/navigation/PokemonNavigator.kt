package org.anfega.pokemon.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import org.anfega.pokemon.presentation.ui.pages.CartScreen
import org.anfega.pokemon.presentation.ui.pages.CatalogScreen
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
            CatalogScreen(navigator)
        }

        scene(route = Routes.CAR) {
            CartScreen(navigator)
        }

    }
}