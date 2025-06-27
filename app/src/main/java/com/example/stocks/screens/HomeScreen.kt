package com.example.stocks.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.stocks.StocksViewModel
import com.example.stocks.components.Carousel
import com.example.stocks.components.StockSearch


//val topGainers = listOf(
//    TopGainerLoser(
//        ticker = "ULY",
//        price = "8.19",
//        changeAmount = "3.71",
//        changePercentage = "82.8125%",
//        volume = "23887434"
//    ),
//    TopGainerLoser(
//        ticker = "VOR",
//        price = "0.5544",
//        changeAmount = "0.2363",
//        changePercentage = "74.2848%",
//        volume = "132871435"
//    ),
//    TopGainerLoser(
//        ticker = "PSTV",
//        price = "0.315",
//        changeAmount = "0.128",
//        changePercentage = "68.4492%",
//        volume = "707153519"
//    ),
//    TopGainerLoser(
//        ticker = "SBEV+",
//        price = "0.0599",
//        changeAmount = "0.0199",
//        changePercentage = "49.75%",
//        volume = "11354"
//    ),
//    TopGainerLoser(
//        ticker = "SLND+",
//        price = "0.21",
//        changeAmount = "0.0681",
//        changePercentage = "47.9915%",
//        volume = "30331"
//    ),
//    TopGainerLoser(
//        ticker = "ASTI",
//        price = "2.84",
//        changeAmount = "1.675",
//        changePercentage = "143.7768%",
//        volume = "110799996"
//    ),
//    TopGainerLoser(
//        ticker = "CYN",
//        price = "13.6",
//        changeAmount = "8.59",
//        changePercentage = "171.4571%",
//        volume = "137178577"
//    ),
//    TopGainerLoser(
//        ticker = "ASTI",
//        price = "2.84",
//        changeAmount = "1.675",
//        changePercentage = "143.7768%",
//        volume = "110799996"
//    ),
//    TopGainerLoser(
//        ticker = "ADIL",
//        price = "0.4698",
//        changeAmount = "0.2238",
//        changePercentage = "90.9756%",
//        volume = "272354215"
//    ),
//    TopGainerLoser(
//        ticker = "LIVE",
//        price = "15.7",
//        changeAmount = "7.25",
//        changePercentage = "85.7988%",
//        volume = "2582524"
//    ),
//    TopGainerLoser(
//        ticker = "BIAFW",
//        price = "0.2251",
//        changeAmount = "0.0951",
//        changePercentage = "73.1538%",
//        volume = "17062"
//    ),
//    TopGainerLoser(
//        ticker = "ESGLW",
//        price = "0.063",
//        changeAmount = "0.0262",
//        changePercentage = "71.1957%",
//        volume = "291038"
//    ),
//)
//
//val topLosers = listOf(
//    TopGainerLoser(
//        ticker = "CYN",
//        price = "13.6",
//        changeAmount = "8.59",
//        changePercentage = "171.4571%",
//        volume = "137178577"
//    ),
//    TopGainerLoser(
//        ticker = "ASTI",
//        price = "2.84",
//        changeAmount = "1.675",
//        changePercentage = "143.7768%",
//        volume = "110799996"
//    ),
//    TopGainerLoser(
//        ticker = "ADIL",
//        price = "0.4698",
//        changeAmount = "0.2238",
//        changePercentage = "90.9756%",
//        volume = "272354215"
//    ),
//    TopGainerLoser(
//        ticker = "LIVE",
//        price = "15.7",
//        changeAmount = "7.25",
//        changePercentage = "85.7988%",
//        volume = "2582524"
//    ),
//    TopGainerLoser(
//        ticker = "BIAFW",
//        price = "0.2251",
//        changeAmount = "0.0951",
//        changePercentage = "73.1538%",
//        volume = "17062"
//    ),
//    TopGainerLoser(
//        ticker = "ESGLW",
//        price = "0.063",
//        changeAmount = "0.0262",
//        changePercentage = "71.1957%",
//        volume = "291038"
//    ),
//)

@Composable
fun HomeScreen(
    viewModel: StocksViewModel,
    modifier: Modifier = Modifier,
) {
    // all state variables
    val topGainers by viewModel.topGainers.collectAsState()
    val topLosers by viewModel.topLosers.collectAsState()

    LaunchedEffect(true) {
        // run when the screen is first composed
        viewModel.getTopGainersAndLosers()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        StockSearch(viewModel)
        Carousel("Top Gainers", topGainers, true)
        Carousel("Top Losers", topLosers, false)
    }
}
