package com.example.pokescroll.presentation.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokescroll.domain.usecase.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel will hold the "State" of the screen (Loading, Success, or Error).
 * Placing this in presentation/home keeps UI logic localized to the Home screen.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val useCases: PokemonUseCases) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            // For now, we fetch a single Pokemon (ID 1) to test the flow
            val result = useCases.getPokemonDetails(1)

            result.onSuccess { pokemon ->
                _state.value = _state.value.copy(
                    pokemonList = listOf(pokemon),
                    isLoading = false
                )
            }.onFailure { error ->
                _state.value = _state.value.copy(
                    error = error.message ?: "An unknown error occurred",
                    isLoading = false
                )
            }
        }
    }
}