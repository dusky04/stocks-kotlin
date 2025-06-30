package com.example.stocks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.BuildConfig
import com.example.stocks.data.api.FetcherInstance
import com.example.stocks.data.api.NetworkResponse
import com.example.stocks.data.model.TopGainerLoser
import com.example.stocks.data.model.TopGainersAndLosersData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopGainersLoserViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    private val _topLosersAndGainers =
        MutableStateFlow<NetworkResponse<TopGainersAndLosersData>>(NetworkResponse.Loading)
    val topLosersAndGainers: StateFlow<NetworkResponse<TopGainersAndLosersData>> =
        _topLosersAndGainers.asStateFlow()

    private val _topGainers = MutableStateFlow<List<TopGainerLoser>>(emptyList())
    val topGainers: StateFlow<List<TopGainerLoser>> = _topGainers.asStateFlow()

    private val _topLosers = MutableStateFlow<List<TopGainerLoser>>(emptyList())
    val topLosers: StateFlow<List<TopGainerLoser>> = _topLosers.asStateFlow()

    fun getTopGainersAndLosers() {
        _topLosersAndGainers.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response =
                    apiInstance.getTopGainersAndLosers("TOP_GAINERS_LOSERS", BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _topLosersAndGainers.value = NetworkResponse.Success(data)
                        _topGainers.value = data.topGainers ?: emptyList()
                        _topLosers.value = data.topLosers ?: emptyList()
                    }
                } else {
                    _topLosersAndGainers.value = NetworkResponse.Error("Empty response body")
                }
            } catch (e: Exception) {
                _topLosersAndGainers.value = NetworkResponse.Error("Network Error")
                Log.i("ERROR: In getTopGainersAndLosers()", e.toString())
            }
        }
    }
}