package com.example.stocks.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.stocks.StocksViewModel
import com.example.stocks.components.Carousel
import com.example.stocks.components.Search
import com.example.stocks.data.TopGainerLoser


val topGainers = listOf(
    TopGainerLoser(
        ticker = "ULY",
        price = "8.19",
        changeAmount = "3.71",
        changePercentage = "82.8125%",
        volume = "23887434"
    ),
    TopGainerLoser(
        ticker = "VOR",
        price = "0.5544",
        changeAmount = "0.2363",
        changePercentage = "74.2848%",
        volume = "132871435"
    ),
    TopGainerLoser(
        ticker = "PSTV",
        price = "0.315",
        changeAmount = "0.128",
        changePercentage = "68.4492%",
        volume = "707153519"
    ),
    TopGainerLoser(
        ticker = "SBEV+",
        price = "0.0599",
        changeAmount = "0.0199",
        changePercentage = "49.75%",
        volume = "11354"
    ),
    TopGainerLoser(
        ticker = "SLND+",
        price = "0.21",
        changeAmount = "0.0681",
        changePercentage = "47.9915%",
        volume = "30331"
    )
)

val topLosers = listOf(
    TopGainerLoser(
        ticker = "ULY",
        price = "8.19",
        changeAmount = "3.71",
        changePercentage = "82.8125%",
        volume = "23887434"
    ),
    TopGainerLoser(
        ticker = "VOR",
        price = "0.5544",
        changeAmount = "0.2363",
        changePercentage = "74.2848%",
        volume = "132871435"
    ),
    TopGainerLoser(
        ticker = "PSTV",
        price = "0.315",
        changeAmount = "0.128",
        changePercentage = "68.4492%",
        volume = "707153519"
    ),
    TopGainerLoser(
        ticker = "SBEV+",
        price = "0.0599",
        changeAmount = "0.0199",
        changePercentage = "49.75%",
        volume = "11354"
    ),
    TopGainerLoser(
        ticker = "SLND+",
        price = "0.21",
        changeAmount = "0.0681",
        changePercentage = "47.9915%",
        volume = "30331"
    )
)

@Composable
fun HomeScreen(
    viewModel: StocksViewModel,
    modifier: Modifier = Modifier,
//    navController: NavHostController
) {
    // all state variables
//    val topGainers by viewModel.topGainers.collectAsState()
//    val topLosers by viewModel.topLosers.collectAsState()

    LaunchedEffect(true) {
        // run when the screen is first composed
//        viewModel.getTopGainersAndLosers()
    }

    Column(modifier = Modifier.fillMaxWidth()) {

        Search()

        Carousel("Top Gainers", topGainers, true)
        Carousel("Top Losers", topLosers, false)
    }
}
