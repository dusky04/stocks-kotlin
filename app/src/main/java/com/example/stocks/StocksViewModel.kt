package com.example.stocks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocks.api.FetcherInstance
import com.example.stocks.data.CompanyOverviewData
import com.example.stocks.data.TickerSearchData
import com.example.stocks.data.TopGainerLoser
import com.example.stocks.data.TopGainersAndLosersData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// TODO: Handle exception when the network request fails

class StocksViewModel : ViewModel() {
    private val apiInstance = FetcherInstance.stocksAPI


    private val _tickerSearchResults =
        MutableStateFlow<TickerSearchData>(TickerSearchData(null))
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

    private val _topLosersAndGainers = MutableStateFlow<TopGainersAndLosersData>(
        TopGainersAndLosersData(
            null, null, null, null, null
        )
    )
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

    private val _companyOverviewData = MutableStateFlow<CompanyOverviewData>(
        CompanyOverviewData(
            address = null,
            analystRatingBuy = null,
            analystRatingHold = null,
            analystRatingSell = null,
            analystRatingStrongBuy = null,
            analystRatingStrongSell = null,
            analystTargetPrice = null,
            assetType = null,
            beta = null,
            bookValue = null,
            cIK = null,
            country = null,
            currency = null,
            dayMovingAverage50 = null,
            dayMovingAverage200 = null,
            description = null,
            dilutedEPSTTM = null,
            dividendDate = null,
            dividendPerShare = null,
            dividendYield = null,
            eBITDA = null,
            ePS = null,
            eVToEBITDA = null,
            eVToRevenue = null,
            exDividendDate = null,
            exchange = null,
            fiscalYearEnd = null,
            forwardPE = null,
            grossProfitTTM = null,
            industry = null,
            latestQuarter = null,
            marketCapitalization = null,
            name = null,
            officialSite = null,
            operatingMarginTTM = null,
            pEGRatio = null,
            pERatio = null,
            priceToBookRatio = null,
            priceToSalesRatioTTM = null,
            profitMargin = null,
            quarterlyEarningsGrowthYOY = null,
            quarterlyRevenueGrowthYOY = null,
            returnOnAssetsTTM = null,
            returnOnEquityTTM = null,
            revenuePerShareTTM = null,
            revenueTTM = null,
            sector = null,
            sharesOutstanding = null,
            symbol = null,
            trailingPE = null,
            weekHigh = null,
            weekLow = null
        )
    )
    val companyOverviewData: StateFlow<CompanyOverviewData> = _companyOverviewData.asStateFlow()
    fun getCompanyOverviewData(ticker: String) {
        viewModelScope.launch {
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
        }
    }

}