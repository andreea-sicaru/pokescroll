package com.example.pokescroll.domain.usecase


/**
 * As the app grows, we might have 20+ Use Cases. To prevent the ViewModels from having 20 constructor parameters, we cab wrap them in a data class.
 * This allows us to inject one single "Use Case container" into our ViewModels.
 */
data class PokemonUseCases(
    val getPokemonDetails: GetPokemonDetailsUseCase,
    // We will add getPokemonList, toggleFavorite, etc. here later
)