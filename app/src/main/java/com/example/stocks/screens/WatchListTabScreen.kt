package com.example.stocks.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.StocksViewModel
import com.example.stocks.data.CompanyOverviewData
import com.example.stocks.ui.theme.sansFontFamily

@Composable
fun WatchListTabScreen(viewModel: StocksViewModel, watchListName: String) {
    val watchLists by viewModel.watchLists.collectAsState()
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

@Composable
fun StockWatchlistItem(stock: CompanyOverviewData) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stock.symbol ?: "N/A",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = sansFontFamily
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stock.name ?: "No Company Name",
                    style = MaterialTheme.typography.bodyMedium,
                    fontFamily = sansFontFamily,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Market Cap",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    // Simple formatting for large numbers
                    text = formatMarketCap(stock.marketCapitalization),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = sansFontFamily,
                    fontSize = 18.sp
                )
            }
        }
    }
}

private fun formatMarketCap(marketCap: String?): String {
    return marketCap?.toLongOrNull()?.let {
        when {
            it >= 1_000_000_000_000 -> "%.2fT".format(it / 1_000_000_000_000.0)
            it >= 1_000_000_000 -> "%.2fB".format(it / 1_000_000_000.0)
            it >= 1_000_000 -> "%.2fM".format(it / 1_000_000.0)
            else -> it.toString()
        }
    } ?: "N/A"
}