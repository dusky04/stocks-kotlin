package com.example.stocks.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.BuildConfig
import com.example.stocks.api.FetcherInstance
import com.example.stocks.api.NetworkResponse
import com.example.stocks.data.NewsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    private val _newsArticles = MutableStateFlow<NetworkResponse<NewsData>>(NetworkResponse.Loading)
    val newsArticles: StateFlow<NetworkResponse<NewsData>> = _newsArticles.asStateFlow()

    fun getNewsArticles() {
        viewModelScope.launch {
            _newsArticles.value = NetworkResponse.Loading
            try {
                val response = apiInstance.getNewsArticles("NEWS_SENTIMENT", BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    response.body()?.let { stockData ->
                        _newsArticles.value = NetworkResponse.Success(stockData)
                    }
                    Log.i(
                        "RESPONSE TIME SERIES",
                        "Time series data: ${response.body()?.toString()}"
                    )
                } else {
                    _newsArticles.value = NetworkResponse.Error("Empty response body")
                    Log.e("ERROR: In getNewsArticles()", response.message())
                }
            } catch (e: Exception) {
                _newsArticles.value = NetworkResponse.Error("Network Error")
                Log.i("Failed Network Request", "")
            }
        }
    }
}