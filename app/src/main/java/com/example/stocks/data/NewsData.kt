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

val sampleNewsData = NewsData(
    items = "50",
    feed = listOf(
        Feed(
            title = "This Home Run Growth Stock Is Too Good to Ignore",
            url = "https://www.fool.com/investing/general/2025/06/29/this-home-run-growth-stock-is-too-good-to-ignore/",
            timePublished = "20250629T163300",
            authors = listOf("Howard Smith"),
            summary = "The investing community is beginning to take notice of Nebius Group ( NASDAQ: NBIS ) . I say that largely based on the almost 150% surge in its share price since mid-April. There's a reason behind that, though. Nebius has been executing on its plan to grow its revenues to a level that would ...",
            bannerImage = "https://g.foolcdn.com/image/?url=https%3A%2F%2Fg.foolcdn.com%2Feditorial%2Fimages%2F821242%2Fartificial-intelligence-cloud-services.jpg&op=resize&w=700",
            source = "Motley Fool",
            sourceDomain = "www.fool.com",
            overallSentimentLabel = "Somewhat-Bullish"
        )
    )
)


