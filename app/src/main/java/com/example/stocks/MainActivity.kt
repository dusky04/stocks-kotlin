package com.example.stocks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
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
            val stocksViewModel = ViewModelProvider(this)[StocksViewModel::class.java]

            StocksApp(stocksViewModel)
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
fun StocksApp(stocksViewModel: StocksViewModel) {
    val navController = rememberNavController()
    val startDestination = Routes.HOME
    StocksTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Scaffold(
                topBar = { Search() },
                bottomBar = { BottomNavBar(navController) }) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = startDestination.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    Routes.entries.forEach { route ->
                        composable(route.route) {
                            when (route) {
                                Routes.HOME -> HomeScreen(stocksViewModel)
                                Routes.WATCH -> WatchListScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Search() {
    var expanded by rememberSaveable { mutableStateOf(false) }

    val textFieldState = rememberTextFieldState()
    SearchBar(
        inputField = {
            SearchBarDefaults.InputField(
                query = textFieldState.text.toString(),
                onQueryChange = { newQuery ->
                    textFieldState.edit {
                        replace(0, length, newQuery)
                    }
                },
                onSearch = {
                    Log.i("SEARCH TRIGGERD", expanded.toString())
                },
                expanded = expanded,
                onExpandedChange = { it -> { expanded = it } },
                placeholder = { Text("Search") },
                leadingIcon = { Icon(Icons.Default.Search, null) }
            )
        }, expanded = expanded, onExpandedChange = { it -> { expanded = it } }
    ) { }
}


