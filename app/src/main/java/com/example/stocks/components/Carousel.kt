package com.example.stocks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stocks.data.TopGainerLoser
import com.example.stocks.ui.theme.sansFontFamily

@Composable
fun Carousel(sectionTitle: String, itemsList: List<TopGainerLoser>, isGainer: Boolean) {
    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            sectionTitle,
            fontFamily = sansFontFamily,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        if (itemsList.isNotEmpty()) {
            val visibleItems = itemsList.take(6)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(itemsList) { item ->
                    StockCard(item, isGainer)
                }
            }
        } else {
            CircularProgressIndicator()
        }
    }
}