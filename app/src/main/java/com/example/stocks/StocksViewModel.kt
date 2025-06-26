package com.example.stocks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.api.FetcherInstance
import kotlinx.coroutines.launch


class StocksViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    fun searchTicker(ticker: String) {
        Log.i("Ticker Name: ", ticker)
        viewModelScope.launch {
            val response =
                apiInstance.getSearchTickerResults("SYMBOL_SEARCH", ticker, BuildConfig.API_KEY)
            if (response.isSuccessful) {
                Log.i("Response: ", response.body().toString())
            } else {
                Log.i("Error: ", response.message())
            }
        }
    }

    // TODO: Change this function name
    fun getTopData() {
        viewModelScope.launch {
            val response =
                apiInstance.getTopGainersAndLosers("TOP_GAINERS_LOSERS", BuildConfig.API_KEY)
            if (response.isSuccessful) {
                Log.i("Response: ", response.body().toString())
            } else {
                Log.i("Error: ", response.message())
            }
        }
    }

    fun getCompanyOverviewData(companyName: String) {
        viewModelScope.launch {
            val response = apiInstance.getCompanyOverview(
                "OVERVIEW", companyName, BuildConfig.API_KEY
            )
            if (response.isSuccessful) {
                Log.i("Response: ", response.body().toString())
            } else {
                Log.i("Error: ", response.message())
            }
        }
    }

}