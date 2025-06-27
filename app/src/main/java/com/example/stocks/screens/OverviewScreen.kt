package com.example.stocks.screens

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun OverviewScreen(ticker: String) {
    Scaffold {
        Text("RECEIVED TICKER: $ticker")
    }
}