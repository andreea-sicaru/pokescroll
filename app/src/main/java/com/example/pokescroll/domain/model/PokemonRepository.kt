package com.example.pokescroll.domain.model

/**
 * The contract.
 * Domain Layer asks for a list of Pokemons but it doesn't care if it comes from Retrofit, local DB or mock file.
 */
interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Result<List<PartialPokemon>>

    suspend fun getPokemonDetails(id: Int): Result<Pokemon>

    suspend fun searchPokemon(query: String): Result<Pokemon>
}