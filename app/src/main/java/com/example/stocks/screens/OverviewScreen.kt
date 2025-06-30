package com.example.stocks.screens

import MPLineChart
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.components.BottomSheet
import com.example.stocks.components.InfoLabel
import com.example.stocks.components.PillShapedBox
import com.example.stocks.components.TopNavBar
import com.example.stocks.formatMarketCap
import com.example.stocks.models.CompanyViewModel
import com.example.stocks.models.TimeSeriesViewModel
import com.example.stocks.models.WatchListViewModel
import com.example.stocks.ui.theme.backgroundColors
import com.example.stocks.ui.theme.sansFontFamily

// FIXME: Can't really show change amount or market status from this endpoint!

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    companyViewModel: CompanyViewModel,
    watchListViewModel: WatchListViewModel,
    timeSeriesViewModel: TimeSeriesViewModel,
    ticker: String,
) {
    val companyOverviewData by companyViewModel.companyOverviewData.collectAsState()
    val watchLists by watchListViewModel.watchLists.collectAsState()
    val timeSeriesData by timeSeriesViewModel.timeSeriesData.collectAsState()

    val backgroundColor = remember { backgroundColors.random() }

    LaunchedEffect(ticker) {
        // Get the company data through ticker name
        companyViewModel.getCompanyOverviewData(ticker)
        timeSeriesViewModel.getIntradayTimeSeries(ticker)
    }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopNavBar("Overview") },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Save") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(24.dp)
                ) {
                    Text(
                        text = ticker,
                        style = MaterialTheme.typography.labelLarge,
                        fontFamily = sansFontFamily,
                        letterSpacing = 1.2.sp,
                        color = Color.White
                    )
                    Text(
                        text = companyOverviewData.name ?: "",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        fontFamily = sansFontFamily,
                        color = Color.White
                    )
                }
            }

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    items(
                        listOf(
                            companyOverviewData.exchange ?: "",
                            companyOverviewData.sector ?: "",
                            companyOverviewData.industry ?: ""
                        )
                    ) { label ->
                        PillShapedBox(label)
                    }
                }
            }

            Column {
                timeSeriesData.timeSeries?.let { data ->
                    MPLineChart(
                        data = data,
                        lineColor = backgroundColor
                    )
                }
            }

            // Info Cards Section
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                HorizontalDivider()
                InfoLabel("Market Cap", formatMarketCap(companyOverviewData.marketCapitalization))
                InfoLabel("P/E Ratio", companyOverviewData.pERatio ?: "")
                InfoLabel("EPS", companyOverviewData.ePS ?: "")
                InfoLabel("Dividend Yield", companyOverviewData.dividendYield ?: "")
                InfoLabel("52W Low", "$" + companyOverviewData.weekLow ?: "")
                InfoLabel("52W High", "$" + companyOverviewData.weekHigh ?: "")
            }

            // About Section
            Column {
                Text(
                    text = "About",
                    fontFamily = sansFontFamily,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                Text(
                    text = companyOverviewData.description ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    lineHeight = 20.sp
                )
            }
        }

        if (showBottomSheet) {
            BottomSheet(
                onDismiss = { showBottomSheet = false },
                sheetState = sheetState,
                stock = companyOverviewData,
                availableWatchLists = watchLists.keys.toList(),
                watchListViewModel = watchListViewModel,
                onSave = { stockTicker, selectedLists ->
                    watchListViewModel.addStockToWatchLists(companyOverviewData, selectedLists)
                    showBottomSheet = false
                }
            )
        }
    }
}