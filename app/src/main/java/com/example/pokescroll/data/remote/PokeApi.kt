package com.example.pokescroll.data.remote

import com.example.pokescroll.data.remote.dto.PokemonDto
import com.example.pokescroll.data.remote.dto.PokemonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int
    ): PokemonDto

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponseDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}