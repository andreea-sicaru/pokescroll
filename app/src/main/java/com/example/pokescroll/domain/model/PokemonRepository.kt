package com.example.pokescroll.domain.model

import androidx.compose.ui.geometry.Offset

/**
 * The contract.
 * Domain Layer asks for a list of Pokemons but it doesn't care if it comes from Retrofit, local DB or mock file.
 */
interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Result<List<Pokemon>>

    suspend fun getPokemonDetails(id: Int): Result<Pokemon>

    suspend fun searchPokemon(query: String): Result<Pokemon>
}