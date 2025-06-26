package com.example.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.stocks.components.BottomNavBar
import com.example.stocks.screens.HomeScreen
import com.example.stocks.ui.theme.StocksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StocksApp()
        }
    }
}


@Composable
fun StocksApp() {
    StocksTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavBar() }) { innerPadding ->
            HomeScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}




