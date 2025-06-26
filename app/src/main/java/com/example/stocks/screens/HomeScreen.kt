package com.example.stocks.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.example.stocks.StocksViewModel
import com.example.stocks.components.StockCard
import com.example.stocks.data.TopGainerLoser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carousel(sectionTitle: String, itemsList: List<TopGainerLoser>, isGainer: Boolean) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(sectionTitle, style = MaterialTheme.typography.titleLarge)
        if (itemsList.isNotEmpty()) {
            HorizontalUncontainedCarousel(
                itemWidth = 170.dp,
                itemSpacing = 4.dp,
                state = rememberCarouselState { itemsList.count() },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clipToBounds(),
            ) { idx ->
                val item = itemsList[idx]
                StockCard(item, isGainer)

            }
        } else {
            CircularProgressIndicator()
        }
    }
}


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
fun HomeScreen(viewModel: StocksViewModel, modifier: Modifier = Modifier) {
    // all state variables
//    val topGainers by viewModel.topGainers.collectAsState()
//    val topLosers by viewModel.topLosers.collectAsState()

    LaunchedEffect(true) {
        // run when the screen is first composed
//        viewModel.getTopGainersAndLosers()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Carousel("Top Gainers", topGainers, true)
        Carousel("Top Losers", topLosers, false)
    }
}
