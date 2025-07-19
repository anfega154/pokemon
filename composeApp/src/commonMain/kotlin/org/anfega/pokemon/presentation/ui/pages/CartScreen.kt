package org.anfega.pokemon.presentation.ui.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.anfega.pokemon.presentation.ui.component.PokemonCard
import org.anfega.pokemon.presentation.viewmodel.CartViewModel

@Composable
fun CartScreen(navigator: Navigator) {
    val viewModel = remember { CartViewModel() }
    val cartItems by remember { derivedStateOf { viewModel.cartItems } }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.loadCart()
    }

    LazyColumn {
        items(cartItems) { pokemon ->
            PokemonCard(
                name = pokemon.name,
                imageUrl = pokemon.imageUrl,
                price = pokemon.price
            ) {
                scope.launch {
                    viewModel.removeItem(pokemon.id)
                }
            }
        }
    }
}