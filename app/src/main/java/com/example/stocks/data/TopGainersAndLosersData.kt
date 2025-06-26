package com.example.stocks.data

import com.google.gson.annotations.SerializedName

data class MostActivelyTraded(
    @SerializedName("change_amount")
    val changeAmount: String,

    @SerializedName("change_percentage")
    val changePercentage: String,

    val price: String,

    val ticker: String,

    val volume: String
)

data class TopGainer(
    @SerializedName("change_amount")
    val changeAmount: String,

    @SerializedName("change_percentage")
    val changePercentage: String,

    val price: String,

    val ticker: String,

    val volume: String
)

data class TopLoser(
    @SerializedName("change_amount")
    val changeAmount: String,

    @SerializedName("change_percentage")
    val changePercentage: String,

    val price: String,

    val ticker: String,

    val volume: String
)

data class TopGainersAndLosersData(
    @SerializedName("last_updated")
    val lastUpdated: String,

    val metadata: String,

    @SerializedName("most_actively_traded")
    val mostActivelyTraded: List<MostActivelyTraded>,

    @SerializedName("top_gainers")
    val topGainers: List<TopGainer>,

    @SerializedName("top_losers")
    val topLosers: List<TopLoser>
)