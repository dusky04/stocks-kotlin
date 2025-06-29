package com.example.stocks

fun formatMarketCap(marketCap: String?): String {
    return marketCap?.toLongOrNull()?.let {
        when {
            it >= 1_000_000_000_000 -> "%.2fT".format(it / 1_000_000_000_000.0)
            it >= 1_000_000_000 -> "%.2fB".format(it / 1_000_000_000.0)
            it >= 1_000_000 -> "%.2fM".format(it / 1_000_000.0)
            else -> it.toString()
        }
    } ?: "N/A"
}