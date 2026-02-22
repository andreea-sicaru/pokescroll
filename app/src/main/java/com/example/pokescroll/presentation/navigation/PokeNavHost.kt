package com.example.pokescroll.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.pokescroll.presentation.home.HomeScreen


/**
 * The NavDisplay is the Nav3 equivalent of NavHost. It observes your backstack (a list of Keys) and resolves them into screens.
 *
 * State is Visible: You can literally see your navigation history by printing the backStack list.
 * Explicit Intent: To navigate, you just add to the list. To go back, you remove.
 * Type Safety: Route.Details(id) ensures you can't forget a required parameter at compile time.
 */
@Composable
fun PokeNavHost() {
    /**
     * We use rememberNavBackStack to persist navigation state through process death.
     * This allows you to initialize a backstack with multiple screens (e.g., deep linking into a detail screen with the home screen underneath it) by calling rememberNavBackStack(Route.Home, Route.Details(id)).
     */
    val backStack = rememberNavBackStack(Route.Home)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
        entryProvider = { key ->
            when (key) {
                is Route.Home -> NavEntry(key) {
                    HomeScreen()
//                    HomeScreen(onNavigateToDetails = { id -> backStack.add(Route.Details(id)) })
                }

                else -> {
                    throw IllegalArgumentException("Invalid route: $key")
                }
//                is Route.Details -> NavEntry(key) {
//                    DetailsScreen(pokemonId = key.pokemonId)
//                }
//                is Route.Favourites -> NavEntry(key) {
//                    FavouritesScreen()
//                }
            }
        })
}