package com.example.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stocks.components.BottomNavBar
import com.example.stocks.screens.HomeScreen
import com.example.stocks.screens.WatchListScreen
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


enum class Routes(
    val label: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
) {
    HOME("Home", Icons.Outlined.Home, Icons.Filled.Home, "home"),
    WATCH(
        "WatchList",
        Icons.Outlined.FavoriteBorder,
        Icons.Filled.Favorite,
        "watch"
    )
}

@Composable
fun StocksApp() {
    val navController = rememberNavController()
    val startDestination = Routes.HOME
    StocksTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomNavBar(navController) }) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = startDestination.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                Routes.entries.forEach { route ->
                    composable(route.route) {
                        when (route) {
                            Routes.HOME -> HomeScreen()
                            Routes.WATCH -> WatchListScreen()
                        }
                    }
                }
            }
        }
    }
}




