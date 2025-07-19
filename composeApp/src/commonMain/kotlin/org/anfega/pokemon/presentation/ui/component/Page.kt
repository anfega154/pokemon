package org.anfega.pokemon.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.websocket.Frame
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page(
    navigator: Navigator,
    showTopBar: Boolean = true,
    showBackButton: Boolean = true,
    showCartIcon: Boolean = true,
    cartCount: Int = 0,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onCartClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    val canGoBack by navigator.canGoBack.collectAsState(initial = false)
    Scaffold(
        topBar = {
            if (showTopBar) {
                TopAppBar(
                    title = { Frame.Text("Pokémon") },
                    navigationIcon = {
                        if (showBackButton && canGoBack) {
                            IconButton(onClick = { navigator.goBack() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                            }
                        } else null
                    },
                    actions = {
                        if (showCartIcon) {
                            Box {
                                IconButton(onClick = onCartClick) {
                                    Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                                }
                                if (cartCount > 0) {
                                    Text(
                                        text = cartCount.toString(),
                                        color = Color.White,
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .offset(x = (-4).dp, y = 4.dp)
                                            .background(Color.Red, CircleShape)
                                            .padding(horizontal = 6.dp, vertical = 2.dp),
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    )
}



