package org.anfega.pokemon.presentation.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.anfega.pokemon.presentation.ui.component.PokemonCard
import org.anfega.pokemon.presentation.viewmodel.CatalogViewModel
import org.anfega.pokemon.utils.Routes

@Composable
fun CatalogScreen(navigator: Navigator) {
    val viewModel = CatalogViewModel()
    val list by remember { derivedStateOf { viewModel.pokemonList } }
    val scope = rememberCoroutineScope()

    LazyColumn {
        items(list) { pokemon ->
            PokemonCard(
                name = pokemon.name,
                imageUrl = pokemon.imageUrl,
                price = pokemon.price
            ) {
                scope.launch {
                    viewModel.addToCart(pokemon)
                }
            }
        }
        item {
            LaunchedEffect(Unit) {
                viewModel.loadMore()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            onClick = { navigator.navigate(Routes.CAR) },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
        }
    }
}
