package com.example.stocks.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.StocksViewModel
import com.example.stocks.data.TickerSearchResult
import com.example.stocks.ui.theme.sansFontFamily

// The 'StockResult' data class was removed as it was unused.
// The composables use 'TickerSearchResult' which is assumed to be defined in your data layer.
// data class TickerSearchResult(val symbol: String?, val name: String?, val currency: String?)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockSearch(viewModel: StocksViewModel) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val textFieldState = rememberTextFieldState()
    val tickerSearchResults by viewModel.tickerSearchResults.collectAsState()

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { newQuery ->
                        textFieldState.edit {
                            replace(0, length, newQuery)
                        }
                        // Only search if the query is not blank
                        if (newQuery.isNotBlank()) {
                            viewModel.searchTicker(newQuery)
                        }
                    },
                    onSearch = {
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search stocks", fontFamily = sansFontFamily) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search icon") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            // Use .let for safer handling of nullable list
            tickerSearchResults.bestMatches?.let { results ->
                if (results.isNotEmpty()) {
                    LazyColumn {
                        items(items = results, key = { it.symbol ?: "" }) { stock ->
                            StockResultItem(
                                stock = stock,
                                onStockClick = { selectedStock ->
                                    expanded = false
                                    textFieldState.edit {
                                        replace(0, length, selectedStock.name ?: "")
                                    }
                                }
                            )
                        }
                    }
                }else{
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun StockResultItem(
    stock: TickerSearchResult,
    onStockClick: (TickerSearchResult) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onStockClick(stock) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // This column contains the stock's name, symbol, and currency.
        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Ensure properties are not null before displaying
            Text(
                text = stock.name ?: "N/A",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${stock.symbol ?: ""} · ${stock.currency ?: ""}",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        // Removed the second Column with hardcoded price and empty Row,
        // as this information is not available in the 'TickerSearchResult' data.
    }
}