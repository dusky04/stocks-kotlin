package com.example.stocks.api

import com.example.stocks.data.CompanyOverviewData
import com.example.stocks.data.TickerSearchData
import com.example.stocks.data.TopGainersAndLosersData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StocksAPI {
    @GET("/query")
    suspend fun getSearchTickerResults(
        @Query("function") functionName: String,
        @Query("keywords") searchValue: String,
        @Query("apikey") apiKey: String
    ): Response<TickerSearchData>

    @GET("/query")
    suspend fun getTopGainersAndLosers(
        @Query("function") functionName: String,
        @Query("apikey") apiKey: String
    ): Response<TopGainersAndLosersData>

    @GET("/query")
    suspend fun getCompanyOverview(
        @Query("function") functionName: String,
        @Query("symbol") companyName: String,
        @Query("apikey") apiKey: String
    ): Response<CompanyOverviewData>
}