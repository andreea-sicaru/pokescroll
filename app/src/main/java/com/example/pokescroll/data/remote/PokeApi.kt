package com.example.pokescroll.data.remote

import com.example.pokescroll.data.remote.dto.PokemonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int
    ): PokemonDto

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}