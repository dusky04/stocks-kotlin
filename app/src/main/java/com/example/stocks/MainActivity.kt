package com.example.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.stocks.models.CompanyViewModel
import com.example.stocks.models.SearchViewModel
import com.example.stocks.models.TimeSeriesViewModel
import com.example.stocks.models.TopGainersLoserViewModel
import com.example.stocks.models.WatchListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val stocksViewModel = ViewModelProvider(this)[StocksViewModel::class.java]
            val searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
            val companyViewModel = ViewModelProvider(this)[CompanyViewModel::class.java]
            val timeSeriesViewModel = ViewModelProvider(this)[TimeSeriesViewModel::class.java]
            val watchListViewModel = ViewModelProvider(this)[WatchListViewModel::class.java]
            val topGainersLoserViewModel =
                ViewModelProvider(this)[TopGainersLoserViewModel::class.java]
            StocksApp(
                stocksViewModel,
                searchViewModel,
                companyViewModel,
                timeSeriesViewModel,
                watchListViewModel,
                topGainersLoserViewModel
            )
        }
    }
}

