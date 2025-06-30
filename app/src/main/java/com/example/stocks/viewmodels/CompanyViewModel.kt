package com.example.stocks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.BuildConfig
import com.example.stocks.data.api.FetcherInstance
import com.example.stocks.data.model.CompanyOverviewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CompanyViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI
    private val _companyOverviewData = MutableStateFlow<CompanyOverviewData>(CompanyOverviewData())
    val companyOverviewData: StateFlow<CompanyOverviewData> = _companyOverviewData.asStateFlow()

    fun getCompanyOverviewData(ticker: String) {
        viewModelScope.launch {
            try {
                val response = apiInstance.getCompanyOverview(
                    "OVERVIEW", ticker, BuildConfig.API_KEY
                )
                if (response.isSuccessful) {
                    response.body()?.let { data ->
                        _companyOverviewData.value = data
                    }
                    Log.i("Response: ", response.body().toString())
                } else {
                    Log.i("ERROR: In getCompanyOverviewData() ", response.message())
                }
            } catch (e: Exception) {
                Log.i("Failed Network Request", "")
            }
        }
    }

}