package com.example.stocks

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stocks.components.BottomNavBar
import com.example.stocks.screens.HomeScreen
import com.example.stocks.screens.OverviewScreen
import com.example.stocks.screens.WatchListScreen
import com.example.stocks.ui.theme.StocksTheme


sealed interface Destination {
    val route: String

    data object Home : Destination {
        override val route: String = "home"
    }

    data object WatchList : Destination {
        override val route: String = "watch"
    }

    data class Overview(val ticker: String) : Destination {
        override val route: String = "overview/{ticker}"

        fun routeWithArgs(): String = "overview/$ticker"

        companion object {
            val tickerArg = "ticker"
            val arguments = listOf(navArgument(tickerArg) { type = NavType.StringType })
        }
    }
}


val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavController not provided")
}

@Composable
fun StocksApp(stocksViewModel: StocksViewModel) {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        StocksTheme {
            Surface(
                modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars),
                color = MaterialTheme.colorScheme.background
            ) {
                Scaffold(
//                    topBar = { if (showTopBar) Search() },
                    bottomBar = { BottomNavBar() }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destination.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Destination.Home.route, exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
                                tween(400)
                            )
                        }) {
                            HomeScreen(stocksViewModel)
                        }
                        composable(Destination.WatchList.route) {
                            WatchListScreen()
                        }
                        composable(
                            Destination.Overview("").route,
                            Destination.Overview.arguments,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                    tween(400)
                                )
                            },
//                            popExitTransition = ,
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    tween(400)
                                )
                            }

                        ) { backStackEntry ->
                            val ticker =
                                backStackEntry.arguments?.getString(Destination.Overview.tickerArg)
                            if (ticker != null) {
                                OverviewScreen(ticker)

                            }
                        }
                    }
                }
            }
        }
    }
}

