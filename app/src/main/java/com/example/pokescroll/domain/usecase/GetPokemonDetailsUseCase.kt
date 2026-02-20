package com.example.pokescroll.domain.usecase

import com.example.pokescroll.domain.model.Pokemon
import com.example.pokescroll.domain.model.PokemonRepository
import javax.inject.Inject

/**
 * This lives in the Domain layer.
 * It contains the "business logic"—for example, if we wanted to sort stats alphabetically or filter out specific data before it reaches the UI, we would do it here.
 */

/**
 * Use Case to fetch a single Pokemon's details.
 * In Clean Architecture, each Use Case should do only one thing.
 */
class GetPokemonDetailsUseCase @Inject constructor(
    private val repository: PokemonRepository
){

    /**
     * By using Kotlin’s invoke operator, we can call the use case in the ViewModel as if it were a function:
     * getPokemonDetailsUseCase(35) instead of getPokemonDetailsUseCase.execute(35).
     * It makes the code cleaner and more readable.
     */
    suspend operator fun invoke(id: Int): Result<Pokemon> {
        // Here you could add logic like:
        // if (id < 0) return Result.failure(Exception("Invalid ID"))

        return repository.getPokemonDetails(id)
    }
}