package com.example.pokescroll.presentation.home

import com.example.pokescroll.domain.model.Pokemon

/**
 * Instead of multiple variables, we wrap everything into one HomeState.
 */
data class HomeState(
    val pokemonList: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)