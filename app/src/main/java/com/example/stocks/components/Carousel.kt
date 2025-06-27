package com.example.stocks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stocks.LocalNavController
import com.example.stocks.data.TopGainerLoser
import com.example.stocks.ui.theme.sansFontFamily

@Composable
fun Carousel(sectionTitle: String, itemsList: List<TopGainerLoser>, isGainer: Boolean) {
    val navController = LocalNavController.current
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                sectionTitle,
                fontFamily = sansFontFamily,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            ElevatedButton(onClick = {
                navController.navigate("toplist/$isGainer")
            }) {
                Text("View All", fontFamily = sansFontFamily)
            }

        }
        if (itemsList.isNotEmpty()) {
            val visibleItems = itemsList.take(6)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(visibleItems) { item ->
                    StockCard(item, isGainer)
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }
}