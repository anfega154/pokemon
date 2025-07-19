package org.anfega.pokemon.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.websocket.Frame
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navigator: Navigator,
    cartCount: Int,
    onCartClick: () -> Unit
) {
    TopAppBar(
        title = { Frame.Text(title) },
        actions = {
            Box {
                IconButton(onClick = onCartClick) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                }
                if (cartCount > 0) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-4).dp, y = 4.dp)
                            .size(16.dp)
                            .background(Color.Red, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = cartCount.toString(),
                            color = Color.White,
                            fontSize = 10.sp
                        )
                    }
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = { navigator.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

