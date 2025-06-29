package com.example.stocks

import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
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
import com.example.stocks.models.CompanyViewModel
import com.example.stocks.models.NewsViewModel
import com.example.stocks.models.SearchViewModel
import com.example.stocks.models.TimeSeriesViewModel
import com.example.stocks.models.TopGainersLoserViewModel
import com.example.stocks.models.WatchListViewModel
import com.example.stocks.screens.HomeScreen
import com.example.stocks.screens.NewsScreen
import com.example.stocks.screens.OverviewScreen
import com.example.stocks.screens.TopListScreen
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

    data object News : Destination {
        override val route: String = "news"
    }

    data class Overview(
        val ticker: String,
    ) : Destination {
        override val route: String = "overview/{ticker}"

        companion object {
            const val TICKER_ARG = "ticker"
            val arguments = listOf(
                navArgument(TICKER_ARG) { type = NavType.StringType },
            )
        }
    }

    data class TopList(
        val kind: Boolean
    ) : Destination {
        override val route: String = "toplist/{kind}"

        companion object {
            const val KIND = "kind"
            val arguments = listOf(
                navArgument(KIND) { type = NavType.BoolType })
        }
    }
}


val LocalNavController = staticCompositionLocalOf<NavHostController> {
    error("NavController not provided")
}

@Composable
fun StocksApp(
    stocksViewModel: StocksViewModel,
    searchViewModel: SearchViewModel,
    companyViewModel: CompanyViewModel,
    timeSeriesViewModel: TimeSeriesViewModel,
    watchListViewModel: WatchListViewModel,
    topGainersLoserViewModel: TopGainersLoserViewModel,
    newsViewModel: NewsViewModel
) {
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
                            )
                        }) {
                            HomeScreen(stocksViewModel, newsViewModel)
                        }
                        composable(Destination.WatchList.route) {
                            WatchListScreen(watchListViewModel)
                        }
                        composable(Destination.News.route) {
                            NewsScreen(newsViewModel)
                        }
                        composable(
                            Destination.Overview("").route,
                            Destination.Overview.arguments,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Left,
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentTransitionScope.SlideDirection.Right,
                                )
                            }

                        ) { backStackEntry ->
                            val args = backStackEntry.arguments
                            val ticker = args?.getString(Destination.Overview.TICKER_ARG)
                            if (ticker != null) {
                                OverviewScreen(
                                    stocksViewModel, watchListViewModel, ticker
                                )
                            } else {
                                Log.e(
                                    "NavigationError",
                                    "OverviewScreen received incomplete arguments."
                                )
                            }
                        }
                        composable(
                            Destination.TopList(true).route, Destination.TopList.arguments
                        ) { backStackEntry ->
                            val kind =
                                backStackEntry.arguments?.getBoolean(Destination.TopList.KIND)
                            if (kind != null) {
                                TopListScreen(stocksViewModel, kind)
                            }
                        }
                    }
                }
            }
        }
    }
}

