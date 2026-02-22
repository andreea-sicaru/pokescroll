package com.example.pokescroll.data.repository

import com.example.pokescroll.data.mapper.toDomain
import com.example.pokescroll.data.remote.PokeApi
import com.example.pokescroll.domain.model.PartialPokemon
import com.example.pokescroll.domain.model.Pokemon
import com.example.pokescroll.domain.model.PokemonRepository
import javax.inject.Inject

/**
 * This lives in the Data layer because it depends on the PokeApi (a framework-specific library).
 * However, it implements an interface from the Domain layer, allowing our business logic to stay decoupled.
 */
class PokemonRepositoryImpl @Inject constructor(private val api: PokeApi) : PokemonRepository {
    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Result<List<PartialPokemon>> {
        return try {
            val response = api.getPokemons(limit, offset)
            Result.success(response.results.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonDetails(id: Int): Result<Pokemon> {
        return try {
            val response = api.getPokemonDetails(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun searchPokemon(query: String): Result<Pokemon> {
        TODO("Implementing in Search Milestone")
    }

}