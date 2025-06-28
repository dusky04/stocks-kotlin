package com.example.stocks.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.stocks.StocksViewModel

@Composable
fun WatchListTabScreen(viewModel: StocksViewModel, idx: Int) {
    Text("Watchlist Tab ${idx + 1}")
}