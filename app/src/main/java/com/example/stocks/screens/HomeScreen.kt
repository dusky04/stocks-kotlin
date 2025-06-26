package com.example.stocks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stocks.StocksViewModel
import com.example.stocks.data.TopGainer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: StocksViewModel, modifier: Modifier = Modifier) {

    val topGainersAndLosers by viewModel.topLosersAndGainers.collectAsState()

    LaunchedEffect(true) {
        // run when the screen is first composed
        viewModel.getTopGainersAndLosers()
    }

    Column {
        Text("Home Screen", modifier = modifier)
        Button(
            onClick = { viewModel.searchTicker("AAPL") }) { Text("Click Here") }
        Button(
            onClick = { viewModel.getTopGainersAndLosers() }) { Text("Click Here for top gaines") }
        Button(
            onClick = { viewModel.getCompanyOverviewData("AAPL") }) { Text("Click Here to get company data") }


        if (topGainersAndLosers.topGainers != null)
            HorizontalUncontainedCarousel(
                itemWidth = 170.dp,
                state = rememberCarouselState { topGainersAndLosers.topGainers!!.count() },
                modifier = Modifier.fillMaxWidth()
            ) { idx ->
                val item = topGainersAndLosers.topGainers!![idx]
                CardComponent(item)
            }
    }
}

@Composable
fun CardComponent(cardInfo: TopGainer) {
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(8.dp)
            .width(170.dp)
            .height(130.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    cardInfo.ticker,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(color = Color(0xFFE0D7C8), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "$${cardInfo.price}",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = null,
                        tint = Color(0xFF2ECC71),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = cardInfo.changePercentage,
                        color = Color(0xFF2ECC71),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
