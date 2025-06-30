package com.example.stocks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.BuildConfig
import com.example.stocks.data.api.FetcherInstance
import com.example.stocks.data.model.TimeSeriesGraphData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TimeSeriesViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI

    private val _timeSeriesGraphData =
        MutableStateFlow<TimeSeriesGraphData>(TimeSeriesGraphData(null, null))
    val timeSeriesData: StateFlow<TimeSeriesGraphData> =
        _timeSeriesGraphData.asStateFlow()

    fun getIntradayTimeSeries(ticker: String) {
        viewModelScope.launch {
            try {
                val response = apiInstance.getIntradayTimeSeries(
                    "TIME_SERIES_INTRADAY",
                    ticker,
                    "5min",
                    BuildConfig.API_KEY
                )
                if (response.isSuccessful) {
                    response.body()?.let { stockData ->
                        _timeSeriesGraphData.value = stockData
                    }
                    Log.i(
                        "RESPONSE TIME SERIES",
                        "Time series data: ${response.body()?.timeSeries?.keys.toString()}"
                    )
                } else {
                    Log.e("ERROR: In getIntradayTimeSeries()", response.message())
                }
            } catch (e: Exception) {
                Log.i("Failed Network Request", "")
            }
        }
    }
}