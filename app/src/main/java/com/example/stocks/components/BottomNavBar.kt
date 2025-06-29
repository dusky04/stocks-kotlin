package com.example.stocks.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.stocks.Destination
import com.example.stocks.LocalNavController
import com.example.stocks.assets.Newspaper


data class BottomNavItem(
    val destination: Destination,
    val label: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)

val bottomNavItems = listOf(
    BottomNavItem(Destination.Home, "Home", Icons.Outlined.Home, Icons.Filled.Home),
    BottomNavItem(
        Destination.WatchList, "WatchList", Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite
    ),
    BottomNavItem(
        Destination.News, "News", Newspaper, Newspaper
    )
)


@Composable
fun BottomNavBar() {
    val navController = LocalNavController.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = (currentRoute == item.destination.route),
                onClick = {
                    navController.navigate(route = item.destination.route) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        if (currentRoute == item.destination.route) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label
                    )
                },
                label = { Text(item.label) },
            )
        }
    }
}
