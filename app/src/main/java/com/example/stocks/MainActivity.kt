package com.example.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.stocks.ui.StocksApp
import com.example.stocks.viewmodels.CompanyViewModel
import com.example.stocks.viewmodels.NewsViewModel
import com.example.stocks.viewmodels.SearchViewModel
import com.example.stocks.viewmodels.TimeSeriesViewModel
import com.example.stocks.viewmodels.TopGainersLoserViewModel
import com.example.stocks.viewmodels.WatchListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
            val companyViewModel = ViewModelProvider(this)[CompanyViewModel::class.java]
            val timeSeriesViewModel = ViewModelProvider(this)[TimeSeriesViewModel::class.java]
            val watchListViewModel = ViewModelProvider(this)[WatchListViewModel::class.java]
            val topGainersLoserViewModel =
                ViewModelProvider(this)[TopGainersLoserViewModel::class.java]
            val newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
            StocksApp(
                searchViewModel,
                companyViewModel,
                timeSeriesViewModel,
                watchListViewModel,
                topGainersLoserViewModel,
                newsViewModel
            )
        }
    }
}

