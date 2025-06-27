package com.example.stocks.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.stocks.StocksViewModel

@Composable
fun TopListScreen(viewModel: StocksViewModel, kind: Boolean) {
    val topGainers by viewModel.topGainers.collectAsState()

//    Text("TOP LIST SCREEN!")
    if (kind) {
        Text("TOP GAINERS")
    } else
        Text("TOP LOSERS")
}