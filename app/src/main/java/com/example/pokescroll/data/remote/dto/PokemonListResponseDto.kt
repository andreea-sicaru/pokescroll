package com.example.pokescroll.data.remote.dto

data class PokemonListResponseDto(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PartialPokemonDto>
)

data class PartialPokemonDto(
    val name: String,
    val url: String
)
