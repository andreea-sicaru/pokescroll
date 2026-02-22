package com.example.pokescroll.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokescroll.domain.usecase.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: PokemonUseCases,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsState())
    val state: StateFlow<DetailsState> = _state.asStateFlow()

    fun getDetails(id: Int) {
        // Prevent re-fetching if we already have this Pokemon loaded
        if (_state.value.pokemon?.id == id) return

        viewModelScope.launch {
            //we used _state.update { ... }. In 2026, this is preferred over _state.value = ... because it is thread-safe. If multiple events happen at once, update ensures you are modifying the most recent version of the state.
            _state.update { it.copy(isLoading = true) }

            val result = useCases.getPokemonDetails(id)

            result.onSuccess { pokemon ->
                _state.update { it.copy(pokemon = pokemon, isLoading = false) }
            }.onFailure { error ->
                _state.update {
                    it.copy(error = error.message ?: "Failed to load", isLoading = false)
                }
            }
        }
    }

}