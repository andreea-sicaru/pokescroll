package com.example.pokescroll.domain.model

/**
 * Domain model - should contain only what the UI actually needs to display
 */
data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val stats: List<PokemonStat>,
    val isFavourite: Boolean = false // Default to false
)

data class PokemonStat(
    val name: String,
    val value: Int
)
