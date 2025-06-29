package com.example.stocks.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// creates a singleton instance through the application
object FetcherInstance {
//    private const val BASE_URL = "http://www.alphavantage.co"
    private const val BASE_URL = "http://192.168.29.83:5000/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private fun createInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val stocksAPI: StocksAPI = createInstance().create(StocksAPI::class.java)
}