package com.example.pokescroll.presentation.search

import com.example.pokescroll.domain.model.PartialPokemon


data class SearchState(
    val results: List<PartialPokemon> = emptyList(),
    val query: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)
