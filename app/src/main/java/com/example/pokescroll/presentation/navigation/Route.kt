package com.example.pokescroll.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable


/**
 * In Nav3, routes are called Keys. They must be serializable to survive process death.
 */
@Serializable
sealed class Route : NavKey {
    @Serializable
    data object Home : Route()
    @Serializable
    data class Details(val pokemonId: Int) : Route()
    @Serializable
    data object Favourites : Route()
}