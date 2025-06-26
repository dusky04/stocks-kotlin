package com.example.stocks.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stocks.StocksViewModel
import com.example.stocks.data.TopGainer

@Composable
fun HomeScreen(viewModel: StocksViewModel, modifier: Modifier = Modifier) {

    LaunchedEffect(true) {
        // `true` will make this run only once
        viewModel.getCompanyOverviewData("AAPL")
    }


    Column {
        CardComponent(stock)

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

val stock = TopGainer("3.71", "82.8125%", "8.19", "ULY", "23887434")


@Composable
fun CardComponent(cardInfo: TopGainer) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFCAF1DE),
        ),
        modifier = Modifier
            .size(width = 150.dp, height = 250.dp)
    ) { Text(cardInfo.ticker) }
}