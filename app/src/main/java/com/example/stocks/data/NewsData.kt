package com.example.stocks.data


import com.google.gson.annotations.SerializedName

data class NewsData(
    @SerializedName("feed")
    val feed: List<Feed?>? = null,
    @SerializedName("items")
    val items: String? = null,
)

data class TickerSentiment(
    @SerializedName("relevance_score")
    val relevanceScore: String? = null,
    @SerializedName("ticker")
    val ticker: String? = null,
    @SerializedName("ticker_sentiment_label")
    val tickerSentimentLabel: String? = null,
    @SerializedName("ticker_sentiment_score")
    val tickerSentimentScore: String? = null
)

data class Feed(
    @SerializedName("authors")
    val authors: List<String?>? = null,
    @SerializedName("banner_image")
    val bannerImage: String? = null,
    @SerializedName("overall_sentiment_label")
    val overallSentimentLabel: String? = null,
    @SerializedName("source")
    val source: String? = null,
    @SerializedName("source_domain")
    val sourceDomain: String? = null,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("time_published")
    val timePublished: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null
)