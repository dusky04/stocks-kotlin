package com.example.stocks.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.stocks.StocksViewModel

@Composable
fun HomeScreen(viewModel: StocksViewModel, modifier: Modifier = Modifier) {
    Column {
        Text("Home Screen", modifier = modifier)
        Button(
            onClick = { viewModel.searchTicker("AAPL") }
        ) { Text("Click Here") }
        Button(
            onClick = { viewModel.getTopData() }
        ) { Text("Click Here for top gaines") }
        Button(
            onClick = { viewModel.getCompanyOverviewData("AAPL") }
        ) { Text("Click Here to get company data") }
    }
}