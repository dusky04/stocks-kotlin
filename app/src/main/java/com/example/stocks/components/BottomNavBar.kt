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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

data class NavBarItem(
    val name: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
)


val navBarItems = listOf(
    NavBarItem(
        name = "Home",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        route = "home"
    ), NavBarItem(
        name = "Watch",
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        route = "watch"
    )
)

@Composable
fun BottomNavBar() {
    var selectedIdx by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        navBarItems.forEachIndexed { idx, item ->
            NavigationBarItem(
                selected = (selectedIdx == idx),
                onClick = { selectedIdx = idx },
                icon = {
                    Icon(
                        imageVector = if (selectedIdx == idx) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.name
                    )
                },
                label = { Text(item.name) },
                alwaysShowLabel = false
            )
        }
    }
}