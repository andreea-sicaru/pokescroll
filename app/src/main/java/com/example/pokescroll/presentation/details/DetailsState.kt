package com.example.pokescroll.presentation.details

import com.example.pokescroll.domain.model.Pokemon

data class DetailsState(
    val pokemon: Pokemon? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)