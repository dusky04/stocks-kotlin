package com.example.stocks.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stocks.components.Carousel
import com.example.stocks.components.StockSearch
import com.example.stocks.models.SearchViewModel
import com.example.stocks.models.TopGainersLoserViewModel


@Composable
fun HomeScreen(
    topGainersLoserViewModel: TopGainersLoserViewModel,
    searchViewModel: SearchViewModel
) {
    // all state variables
    val topGainers by topGainersLoserViewModel.topGainers.collectAsState()
    val topLosers by topGainersLoserViewModel.topLosers.collectAsState()

    LaunchedEffect(true) {
        // run when the screen is first composed
        topGainersLoserViewModel.getTopGainersAndLosers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        StockSearch(searchViewModel)
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Carousel("Top Gainers", topGainers, true)
            Carousel("Top Losers", topLosers, false)
        }
    }
}
