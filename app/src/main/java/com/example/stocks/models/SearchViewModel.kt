package com.example.stocks.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.BuildConfig
import com.example.stocks.api.FetcherInstance
import com.example.stocks.data.TickerSearchData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class SearchViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    private val _tickerSearchResults =
        MutableStateFlow<TickerSearchData>(TickerSearchData())
    val tickerSearchResults: StateFlow<TickerSearchData> = _tickerSearchResults.asStateFlow()

    fun searchTicker(ticker: String) {
        viewModelScope.launch {
            val response =
                apiInstance.getSearchTickerResults("SYMBOL_SEARCH", ticker, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                Log.i("SEARCH RESPONSE", response.body().toString())
                response.body()?.let { data ->
                    _tickerSearchResults.value = data
                }
            } else {
                Log.i("ERROR: In searchTicker() ", response.message())
            }
        }
    }
}