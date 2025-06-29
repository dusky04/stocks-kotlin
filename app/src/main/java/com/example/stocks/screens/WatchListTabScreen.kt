package com.example.stocks.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stocks.components.StockWatchlistItem
import com.example.stocks.models.WatchListViewModel
import com.example.stocks.ui.theme.sansFontFamily

@Composable
fun WatchListTabScreen(watchListViewModel: WatchListViewModel, watchListName: String) {
    val watchLists by watchListViewModel.watchLists.collectAsState()
    val stocksForThisWatchlist = watchLists[watchListName]?.toList() ?: emptyList()

    if (stocksForThisWatchlist.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "This watchlist is empty.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                fontFamily = sansFontFamily
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(stocksForThisWatchlist) { stock ->
                StockWatchlistItem(stock = stock)
            }
        }
    }
}



