// WatchListScreen.kt

package com.example.stocks.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.stocks.StocksViewModel
import com.example.stocks.ui.theme.sansFontFamily


enum class TabRoutes(val label: String) {
    WATCH_ONE("Watchlist 1"),
    WATCH_TWO("Watchlist 2")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListScreen(viewModel: StocksViewModel) { // Accept the ViewModel
    // navController is no longer needed here for tab switching
    val startDestination = TabRoutes.WATCH_ONE
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    // Use a Column to place the TabRow above the content
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = "Watchlist",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = sansFontFamily,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
        )

        ScrollableTabRow(
            selectedTabIndex = selectedDestination,
            modifier = Modifier.fillMaxWidth(),
            edgePadding = 0.dp,
        ) {
            TabRoutes.entries.forEachIndexed { idx, tabRoute ->
                Tab(
                    selected = (selectedDestination == idx),
                    onClick = {
                        // Just update the state. No navigation needed.
                        selectedDestination = idx
                    },
                    text = {
                        Text(
                            text = tabRoute.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = sansFontFamily
                        )
                    }
                )
            }
        }

        // Display the content for the currently selected tab
        WatchListTabScreen(viewModel = viewModel, idx = selectedDestination)
    }
}