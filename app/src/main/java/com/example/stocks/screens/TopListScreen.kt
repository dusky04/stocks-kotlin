package com.example.stocks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stocks.StocksViewModel
import com.example.stocks.data.TopGainerLoser


@Composable
fun TopListScreen(viewModel: StocksViewModel, kind: Boolean) {
    // State Varibles
    val topGainers by viewModel.topGainers.collectAsState()
    val topLosers by viewModel.topLosers.collectAsState()

    val stocksToShow = if (kind) topGainers else topLosers
    val title = if (kind) "Top Gainers" else "Top Losers"

    if (stocksToShow.isNotEmpty()) {
        StockIndicesTable(
            title = title,
            stocks = stocksToShow
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}


@Composable
fun StockIndicesTable(
    title: String = "Top Gainers", stocks: List<TopGainerLoser>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF374151),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Stock items
        stocks.forEach { stock ->
            StockItem(stock = stock)
        }
    }
}

@Composable
fun StockItem(stock: TopGainerLoser) {
    val isPositive = stock.changeAmount.toDoubleOrNull()?.let { it > 0 } ?: false
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .background(
                        color = Color(0xFFE5E7EB), shape = RoundedCornerShape(4.dp)
                    )
                    .padding(
                        horizontal = 8.dp, vertical = 4.dp
                    )
            ) {
                Text(
                    text = stock.ticker,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF6B7280),
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Text(
            text = stock.price,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF374151),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1.0f)
        )

        Text(
            text = if (isPositive) "+${stock.changeAmount}" else stock.changeAmount,
            style = MaterialTheme.typography.bodySmall,
            color = if (isPositive) Color(0xFF059669) else Color(0xFFDC2626),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1.5f)
                .background(
                    color = if (isPositive) Color(0xFFDCFCE7) else Color(0xFFFEE2E2),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Icon(
                imageVector = if (isPositive) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                tint = if (isPositive) Color(0xFF059669) else Color(0xFFDC2626),
                modifier = Modifier.size(16.dp)
            )

            Text(
                text = stock.changePercentage,
                style = MaterialTheme.typography.bodySmall,
                color = if (isPositive) Color(0xFF059669) else Color(0xFFDC2626),
                fontWeight = FontWeight.Medium
            )
        }
    }
}