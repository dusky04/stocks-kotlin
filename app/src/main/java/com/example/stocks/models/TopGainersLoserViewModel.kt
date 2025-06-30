package com.example.stocks.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.BuildConfig
import com.example.stocks.api.FetcherInstance
import com.example.stocks.data.TopGainerLoser
import com.example.stocks.data.TopGainersAndLosersData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopGainersLoserViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    private val _topLosersAndGainers =
        MutableStateFlow<TopGainersAndLosersData>(TopGainersAndLosersData())
    val topLosersAndGainers: StateFlow<TopGainersAndLosersData> = _topLosersAndGainers.asStateFlow()

    private val _topGainers = MutableStateFlow<List<TopGainerLoser>>(emptyList())
    val topGainers: StateFlow<List<TopGainerLoser>> = _topGainers.asStateFlow()

    private val _topLosers = MutableStateFlow<List<TopGainerLoser>>(emptyList())
    val topLosers: StateFlow<List<TopGainerLoser>> = _topLosers.asStateFlow()

    fun getTopGainersAndLosers() {
        viewModelScope.launch {
            val response =
                apiInstance.getTopGainersAndLosers("TOP_GAINERS_LOSERS", BuildConfig.API_KEY)
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    _topLosersAndGainers.value = data
                    _topGainers.value = data.topGainers ?: emptyList()
                    _topLosers.value = data.topLosers ?: emptyList()
                }
            } else {
                Log.i("ERROR: In getTopGainersAndLosers() ", response.message())
            }
        }
    }
}