package com.example.stocks.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.example.stocks.data.TopGainerLoser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carousel(sectionTitle: String, itemsList: List<TopGainerLoser>, isGainer: Boolean) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(sectionTitle, style = MaterialTheme.typography.titleLarge)
        if (itemsList.isNotEmpty()) {
            HorizontalUncontainedCarousel(
                itemWidth = 170.dp,
                itemSpacing = 4.dp,
                state = rememberCarouselState { itemsList.count() },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clipToBounds(),
            ) { idx ->
                val item = itemsList[idx]
                StockCard(item, isGainer)
            }
        } else {
            CircularProgressIndicator()
        }
    }
}