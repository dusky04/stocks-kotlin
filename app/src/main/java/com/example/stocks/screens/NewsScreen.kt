package com.example.stocks.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stocks.components.NewsArticleComponent
import com.example.stocks.data.Feed
import com.example.stocks.data.NewsData
import com.example.stocks.models.NewsViewModel
import com.example.stocks.ui.theme.sansFontFamily

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
        ),
        Feed(
            title = "Consumer Tech News  ( June 23-June 27 ) : Inflation Stirs, Trade Tensions Flare, And Big Tech Makes Big Moves - Apple  ( NASDAQ:AAPL ) , Amazon.com  ( NASDAQ:AMZN ) ",
            url = "https://www.benzinga.com/markets/large-cap/25/06/46159694/consumer-tech-news-june-23-june-27-inflation-stirs-trade-tensions-flare-and-big-tech-makes-big-",
            timePublished = "20250629T161739",
            authors = listOf("Nabaparna Bhattacharya"),
            summary = "May PCE inflation rose to 2.3%, renewing concerns that rising tariffs could squeeze U.S. consumers in coming months. Trump halted trade talks with Canada over a digital tax on U.S. tech firms, vowing tariffs and rattling markets late in the week.",
            bannerImage = "https://cdn.benzinga.com/files/images/story/2025/06/29/Tech-stock-skyrockets.jpeg?width=1200&height=800&fit=crop",
            source = "Benzinga",
            sourceDomain = "www.benzinga.com",
            overallSentimentLabel = "Neutral"
        )
    )
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {
//    val newsArticles by newsViewModel.newsArticles.collectAsState()


    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        TopAppBar(
            title = {
                Text(
                    text = "News",
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = sansFontFamily,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
        )
        LazyColumn {
            itemsIndexed(sampleNewsData.feed?.filterNotNull() ?: emptyList()) { idx, feed ->
                NewsArticleComponent(feed)
            }
        }

    }
}


