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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.stocks.models.WatchListViewModel
import com.example.stocks.ui.theme.sansFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListScreen(watchListViewModel: WatchListViewModel) {
    val watchLists by watchListViewModel.watchLists.collectAsState()
    val availableWatchLists = watchLists.keys.toList()
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = "Watchlist",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = sansFontFamily,
                    modifier = Modifier.fillMaxWidth()
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ), modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
        )

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier.fillMaxWidth(),
            edgePadding = 0.dp,
        ) {
            availableWatchLists.forEachIndexed { idx, tabRoute ->
                Tab(selected = (selectedTabIndex == idx), onClick = {
                    selectedTabIndex = idx
                }, text = {
                    Text(
                        text = tabRoute,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontFamily = sansFontFamily
                    )
                })
            }
        }

        val selectedWatchListName = availableWatchLists.getOrNull(selectedTabIndex)
        if (selectedWatchListName != null) {
            WatchListTabScreen(
                watchListViewModel = watchListViewModel,
                watchListName = selectedWatchListName
            )
        }
    }
}