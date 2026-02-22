package com.example.pokescroll.di

import com.example.pokescroll.domain.model.PokemonRepository
import com.example.pokescroll.domain.usecase.GetPokemonDetailsUseCase
import com.example.pokescroll.domain.usecase.PokemonUseCases
import com.example.pokescroll.domain.usecase.SearchPokemon
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonUseCases(repository: PokemonRepository): PokemonUseCases {
        return PokemonUseCases(
            getPokemonDetails = GetPokemonDetailsUseCase(repository),
            searchPokemon = SearchPokemon(repository)
        )
    }
}
