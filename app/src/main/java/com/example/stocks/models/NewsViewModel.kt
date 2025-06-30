package com.example.stocks.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.BuildConfig
import com.example.stocks.api.FetcherInstance
import com.example.stocks.data.NewsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    private val _newsArticles = MutableStateFlow<NewsData>(NewsData())
    val newsArticles: StateFlow<NewsData> = _newsArticles.asStateFlow()

    fun getNewsArticles() {
        try {
            viewModelScope.launch {
                val response = apiInstance.getNewsArticles("NEWS_SENTIMENT", BuildConfig.API_KEY)
                if (response.isSuccessful) {
                    response.body()?.let { stockData ->
                        _newsArticles.value = stockData
                    }
                    Log.i(
                        "RESPONSE TIME SERIES",
                        "Time series data: ${response.body()?.toString()}"
                    )
                } else {
                    Log.e("ERROR: In getNewsArticles()", response.message())
                }
            }
        } catch (e: Exception) {
            Log.i("Failed Network Request", "")
        }
    }
}