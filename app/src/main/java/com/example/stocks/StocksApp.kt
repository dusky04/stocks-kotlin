package com.example.stocks

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.WindowInsets
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

    data class Overview(
        val ticker: String,
        val price: String,
        val changeAmount: String,
        val changePercentage: String,
    ) : Destination {
        override val route: String = "overview/{ticker}/{price}/{changeAmount}/{changePercentage}"

        fun routeWithArgs(): String = "overview/$ticker/$price/$changeAmount/$changePercentage"

        companion object {
            const val TICKER_ARG = "ticker"
            const val PRICE_ARG = "price"
            const val CHANGE_AMOUNT_ARG = "changeAmount"
            const val CHANGE_PERCENTAGE_ARG = "changePercentage"
            val arguments = listOf(
                navArgument(TICKER_ARG) { type = NavType.StringType },
                navArgument(PRICE_ARG) { type = NavType.StringType },
                navArgument(CHANGE_AMOUNT_ARG) { type = NavType.StringType },
                navArgument(CHANGE_PERCENTAGE_ARG) { type = NavType.StringType }
            )
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
                    bottomBar = { BottomNavBar() }) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Destination.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Destination.Home.route, exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Left,
//                                tween(400)
                            )
                        }) {
                            HomeScreen(stocksViewModel)
                        }
                        composable(Destination.WatchList.route) {
                            WatchListScreen()
                        }
                        composable(
                            Destination.Overview("", "", "", "").route,
                            Destination.Overview.arguments,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
//                                    tween(400)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                    tween(400)
                                )
                            }

                        ) { backStackEntry ->
                            val args = backStackEntry.arguments
                            val ticker = args?.getString(Destination.Overview.TICKER_ARG)
                            val price = args?.getString(Destination.Overview.PRICE_ARG)
                            val changeAmount =
                                args?.getString(Destination.Overview.CHANGE_AMOUNT_ARG)
                            val changePercentage =
                                args?.getString(Destination.Overview.CHANGE_PERCENTAGE_ARG)

                            if (ticker != null && price != null && changeAmount != null && changePercentage != null) {
                                OverviewScreen(
                                    stocksViewModel,
                                    ticker,
                                    price,
                                    changeAmount,
                                    changePercentage
                                )
                            } else {
                                // Handle the case where arguments are missing, e.g., show an error or pop back.
                                // For now, we can just log it.
                                Log.e(
                                    "NavigationError",
                                    "OverviewScreen received incomplete arguments."
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

