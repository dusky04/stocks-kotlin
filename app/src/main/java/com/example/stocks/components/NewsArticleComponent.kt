package com.example.stocks.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil3.compose.AsyncImage
import com.example.stocks.data.Feed

@Composable
fun NewsArticleComponent(newsArticle: Feed) {
    val articleFeed = newsArticle

    Row {

        Text(articleFeed.title.toString())
        AsyncImage(
            model = "https://example.com/image.jpg",
            contentDescription = "Translated description of what the image contains"
        )
    }
}