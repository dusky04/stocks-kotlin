package com.example.stocks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.data.api.FetcherInstance
import com.example.stocks.data.model.CompanyOverviewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WatchListViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    private val _watchLists = MutableStateFlow<Map<String, MutableSet<CompanyOverviewData>>>(
        mapOf(
            "WatchList 1" to mutableSetOf(), "WatchList 2" to mutableSetOf()
        )
    )
    val watchLists: StateFlow<Map<String, MutableSet<CompanyOverviewData>>> =
        _watchLists.asStateFlow()

    // TODO: Perform this function on a background thread
    fun addNewWatchList(newWatchListName: String): Boolean {
        val currentWatchLists = _watchLists.value.toMutableMap()
        if (currentWatchLists.containsKey(newWatchListName)) {
            Log.w("VIEWMODEL", "Watchlist with name '$newWatchListName' already exists.")
            return false
        }
        currentWatchLists.put(newWatchListName, mutableSetOf())
        _watchLists.value = currentWatchLists
        Log.i("VIEWMODEL", "Successfully added new watchlist: $newWatchListName")
        return true
    }

    fun addStockToWatchLists(stock: CompanyOverviewData, selectedWatchLists: List<String>) {
        viewModelScope.launch {
            val currentWatchLists = _watchLists.value.toMutableMap()
            selectedWatchLists.forEach { watchListName ->
                val currentList = currentWatchLists[watchListName]?.toMutableSet() ?: mutableSetOf()
                currentList.add(stock)
                currentWatchLists[watchListName] = currentList
            }
            _watchLists.value = currentWatchLists
            Log.i("VIEWMODEL", "Updated watchLists: ${_watchLists.value}")
        }

    }
}