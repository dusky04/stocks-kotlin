package com.example.stocks.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class TimeSeriesGraphData(
    @SerializedName("Meta Data")
    val metaData: MetaData?,
    @SerializedName("Time Series (5min)")
    val timeSeries: Map<String, TimeSeriesEntry>?
)

data class MetaData(
    @SerializedName("1. Information")
    val information: String?,
    @SerializedName("4. Interval")
    val interval: String?,
    @SerializedName("3. Last Refreshed")
    val lastRefreshed: String?,
    @SerializedName("5. Output Size")
    val outputSize: String?,
    @SerializedName("2. Symbol")
    val symbol: String?,
    @SerializedName("6. Time Zone")
    val timeZone: String?
)

@Serializable
data class TimeSeriesEntry(
    @SerializedName("4. close")
    val close: String?,
    @SerializedName("2. high")
    val high: String?,
    @SerializedName("3. low")
    val low: String?,
    @SerializedName("1. open")
    val open: String?,
    @SerializedName("5. volume")
    val volume: String?
)

val metaData = MetaData(
    information = "Intraday (5min) open, high, low, close prices and volume",
    interval = "5min",
    lastRefreshed = "2025-06-27 19:55:00",
    outputSize = "Compact",
    symbol = "IBM",
    timeZone = "US/Eastern"
)


val entry1 = TimeSeriesEntry(
    open = "289.5000", high = "289.5000", low = "289.5000", close = "289.5000", volume = "66"
)

val entry2 = TimeSeriesEntry(
    open = "289.6500", high = "289.6500", low = "289.6500", close = "289.6500", volume = "35"
)

val entry3 = TimeSeriesEntry(
    open = "289.5200", high = "289.6500", low = "289.5000", close = "289.5200", volume = "154"
)

val entry4 = TimeSeriesEntry(
    open = "289.6500", high = "289.6500", low = "289.5100", close = "289.5100", volume = "18"
)

val entry5 = TimeSeriesEntry(
    open = "289.6000", high = "289.6500", low = "289.6000", close = "289.6400", volume = "109"
)

val entry6 = TimeSeriesEntry(
    open = "289.6500", high = "289.6500", low = "289.6500", close = "289.6500", volume = "3"
)

val entry7 = TimeSeriesEntry(
    open = "289.6400", high = "289.6400", low = "289.6400", close = "289.6400", volume = "50"
)

val entry8 = TimeSeriesEntry(
    open = "289.6500", high = "289.6500", low = "289.4000", close = "289.6400", volume = "22"
)

val entry9 = TimeSeriesEntry(
    open = "289.2500", high = "289.2500", low = "289.1000", close = "289.1000", volume = "3"
)

val entry10 = TimeSeriesEntry(
    open = "289.7000", high = "289.7000", low = "289.5500", close = "289.6500", volume = "1011523"
)


val timeSeriesMap = mapOf(
    "2025-06-27 19:55:00" to entry1,
    "2025-06-27 19:50:00" to entry2,
    "2025-06-27 19:40:00" to entry3,
    "2025-06-27 19:35:00" to entry4,
    "2025-06-27 19:30:00" to entry5,
    "2025-06-27 19:20:00" to entry6,
    "2025-06-27 19:15:00" to entry7,
    "2025-06-27 19:10:00" to entry8,
    "2025-06-27 19:05:00" to entry9,
    "2025-06-27 19:00:00" to entry10
)

val timeSeriesGraphData = TimeSeriesGraphData(
    metaData = metaData,
    timeSeries = timeSeriesMap
)
