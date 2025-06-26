package com.example.stocks.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.stocks.Routes


@Composable
fun BottomNavBar(navController: NavHostController) {
    var selectedIdx by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        Routes.entries.forEachIndexed { idx, item ->
            NavigationBarItem(
                alwaysShowLabel = false,
                selected = (selectedIdx == idx),
                onClick = {
                    navController.navigate(route = item.route)
                    selectedIdx = idx
                },
                label = { Text(item.label) },
                icon = {
                    Icon(
                        imageVector = if (selectedIdx == idx) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                    )
                },
            )
        }
    }
}
