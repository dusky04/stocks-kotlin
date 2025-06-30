package com.example.stocks.data.model

import com.google.gson.annotations.SerializedName

data class MostActivelyTraded(
    @SerializedName("change_amount")
    val changeAmount: String? = null,

    @SerializedName("change_percentage")
    val changePercentage: String? = null,

    val price: String? = null,

    val ticker: String? = null,

    val volume: String? = null
)

data class TopGainerLoser(
    @SerializedName("change_amount")
    val changeAmount: String? = null,

    @SerializedName("change_percentage")
    val changePercentage: String? = null,

    val price: String? = null,

    val ticker: String? = null,

    val volume: String? = null
)


data class TopGainersAndLosersData(
    @SerializedName("last_updated")
    val lastUpdated: String? = null,

    val metadata: String? = null,

    @SerializedName("most_actively_traded")
    val mostActivelyTraded: List<MostActivelyTraded>? = null,

    @SerializedName("top_gainers")
    val topGainers: List<TopGainerLoser>? = null,

    @SerializedName("top_losers")
    val topLosers: List<TopGainerLoser>? = null
)