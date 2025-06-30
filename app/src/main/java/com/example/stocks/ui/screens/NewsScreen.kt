package com.example.stocks.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.stocks.data.api.NetworkResponse
import com.example.stocks.ui.components.NewsArticleComponent
import com.example.stocks.ui.theme.sansFontFamily
import com.example.stocks.viewmodels.NewsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(newsViewModel: NewsViewModel) {
    val newsArticles by newsViewModel.newsArticles.collectAsState()

    LaunchedEffect(true) {
        newsViewModel.getNewsArticles()
    }

    Column(
        modifier = Modifier
            .windowInsetsPadding(WindowInsets.statusBars)
            .fillMaxSize()
    ) {
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

        when (val currentState = newsArticles) {
            is NetworkResponse.Error -> Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text("Error Loading Data") }

            NetworkResponse.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            is NetworkResponse.Success -> {
                val articles = currentState.data.feed
                if (!articles.isNullOrEmpty()) {
                    LazyColumn {
                        itemsIndexed(
                            articles.filterNotNull()
                        ) { idx, feed ->
                            NewsArticleComponent(feed)
                        }
                    }
                }
            }
        }
    }
}


