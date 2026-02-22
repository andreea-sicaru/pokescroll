package com.example.pokescroll.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.pokescroll.presentation.details.DetailsScreen
import com.example.pokescroll.presentation.home.HomeScreen
import com.example.pokescroll.presentation.main.components.PokeBottomBar
import com.example.pokescroll.presentation.navigation.Route
import com.example.pokescroll.presentation.search.SearchScreen

@Composable
fun MainScreen() {
    val backStack = rememberNavBackStack(Route.Home)

    // Derive the current tab from the backstack to highlight the correct icon
    val currentRoute = (backStack.lastOrNull() ?: Route.Home) as Route

    Scaffold(
        bottomBar = {
            PokeBottomBar(
                currentRoute = currentRoute,
                onTabSelected = { selectedRoute ->
                    // Navigation 3 logic: Clear to root and swap
                    if (currentRoute != selectedRoute) {
                        backStack.clear()
                        backStack.add(selectedRoute)
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            /**
             * The NavDisplay is the Nav3 equivalent of NavHost. It observes your backstack (a list of Keys) and resolves them into screens.
             *
             * State is Visible: You can literally see your navigation history by printing the backStack list.
             * Explicit Intent: To navigate, you just add to the list. To go back, you remove.
             * Type Safety: Route.Details(id) ensures you can't forget a required parameter at compile time.
             */
            NavDisplay(
                backStack = backStack,
                onBack = { if (backStack.size > 1) backStack.removeLastOrNull() },
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    // Note: If you find rememberViewModelStoreNavEntryDecorator later, add it here
                ),
                entryProvider = { key ->
                    when (key) {
                        is Route.Home -> NavEntry(key) {
                            HomeScreen(onNavigateToDetails = { id -> backStack.add(Route.Details(id)) })
                        }

                        is Route.Details -> NavEntry(key) {
                            DetailsScreen(pokemonId = key.pokemonId)
                        }

                        is Route.Search -> NavEntry(key) {
                            SearchScreen(onNavigateToDetails = {id -> backStack.add(Route.Details(id))})
//                             SearchScreen() // To be created
//                            Text("Search Screen")
                        }

                        is Route.Favourites -> NavEntry(key) {
                            // FavouritesScreen() // To be created
                            Text("Favourites Screen")
                        }

                        else -> throw IllegalArgumentException("Invalid route: $key")
                    }
                }
            )
        }
    }
}