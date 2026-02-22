package com.example.pokescroll.presentation.main.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.pokescroll.presentation.navigation.Route

@Composable
fun PokeBottomBar(
    currentRoute: Route,
    onTabSelected: (Route) -> Unit
) {
    val items = listOf(
        Triple(Route.Home, "Home", Icons.Default.Home),
        Triple(Route.Search, "Search", Icons.Default.Search),
        Triple(Route.Favourites, "Saved", Icons.Default.Favorite)
    )

    NavigationBar {
        items.forEach { (route, label, icon) ->
            NavigationBarItem(
                selected = currentRoute == route,
                onClick = { onTabSelected(route) },
                icon = { Icon(icon, contentDescription = label) },
                label = { Text(label) }
            )
        }
    }
}