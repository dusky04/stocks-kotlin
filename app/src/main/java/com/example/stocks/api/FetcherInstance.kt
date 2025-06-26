package com.example.stocks.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// creates a singleton instance through the application
object FetcherInstance {
    private const val BASE_URL = "https://www.alphavantage.co"

    private fun createInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stocksAPI: StocksAPI = createInstance().create(StocksAPI::class.java)
}