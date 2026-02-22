package com.example.pokescroll.domain.usecase

import com.example.pokescroll.domain.model.PartialPokemon
import com.example.pokescroll.domain.model.PokemonRepository

/**
 * PokeAPI does not have a "search" endpoint for partial matches (like searching "Pika" to get "Pikachu").
 * To achieve a high-quality user experience, we usually fetch a large list once and filter it locally, or use the direct name endpoint for exact matches.
 */
class SearchPokemon(private val repository: PokemonRepository) {
    suspend operator fun invoke(query: String): Result<List<PartialPokemon>> {
        if (query.isBlank()) return Result.success(emptyList())

        return repository.getPokemonList(limit = 2000, offset = 0).map { list ->
            list.filter { it.name.contains(query, ignoreCase = true) }
        }
    }
}