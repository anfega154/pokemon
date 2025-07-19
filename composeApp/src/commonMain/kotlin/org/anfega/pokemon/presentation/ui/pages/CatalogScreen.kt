package org.anfega.pokemon.presentation.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.Navigator
import org.anfega.pokemon.presentation.ui.component.Page
import org.anfega.pokemon.presentation.ui.component.PokemonCard
import org.anfega.pokemon.presentation.viewmodel.CatalogViewModel
import org.anfega.pokemon.utils.Routes

@Composable
fun CatalogScreen(navigator: Navigator) {
    val viewModel = remember { CatalogViewModel() }
    val list by remember { derivedStateOf { viewModel.pokemonList } }
    val cartCount by remember { derivedStateOf { viewModel.cartCount } }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        scope.launch { viewModel.loadInitial() }
    }

    val onCartClick: () -> Unit = {
        if (cartCount > 0) {
            navigator.navigate(Routes.CAR)
        } else {
            scope.launch {
                snackbarHostState.showSnackbar("No tienes ítems seleccionados")
            }
        }
    }

    Page(
        navigator = navigator,
        showTopBar = true,
        showBackButton = false,
        snackbarHostState = snackbarHostState,
        cartCount = cartCount,
        onCartClick = onCartClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
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
                        scope.launch { viewModel.loadMore() }
                    }
                }
            }

            FloatingActionButton(
                onClick = onCartClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                if (cartCount > 0) {
                    androidx.compose.material3.BadgedBox(
                        badge = {
                            androidx.compose.material3.Badge {
                                androidx.compose.material3.Text(cartCount.toString())
                            }
                        }
                    ) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Ir al carrito")
                    }
                } else {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Ir al carrito")
                }
            }
        }
    }
}



