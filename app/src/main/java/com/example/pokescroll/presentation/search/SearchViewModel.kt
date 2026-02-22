package com.example.pokescroll.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokescroll.domain.usecase.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCases: PokemonUseCases) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null

    fun onQueryChanged(newQuery: String) {
        _state.update { it.copy(query = newQuery) }

        // Debounce: Wait 500ms before triggering the search
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            executeSearch(newQuery)
        }
    }

    private suspend fun executeSearch(query: String) {
        _state.update { it.copy(isLoading = true) }
        val result = useCases.searchPokemon(query)

        result.onSuccess { partialPokemonList ->
            _state.update {
                it.copy(
                    results = partialPokemonList,
                    isLoading = false
                )
            }
        }.onFailure { error ->
            _state.value = _state.value.copy(
                error = error.message ?: "An unknown error occurred",
                isLoading = false
            )
        }
    }

}