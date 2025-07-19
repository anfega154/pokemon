package org.anfega.pokemon.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberAsyncImagePainter


@Composable
fun PokemonCard(name: String, imageUrl: String, price: Double, onAction: () -> Unit) {
    val painter = rememberAsyncImagePainter(imageUrl)
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painter,
                contentDescription = "Pokemon Image",
                modifier = Modifier.size(80.dp).padding(8.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(name.capitalize(), fontWeight = FontWeight.Bold)
                Text("$${price}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = onAction) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    }
}