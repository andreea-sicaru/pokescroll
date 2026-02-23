package com.example.pokescroll.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pokescroll.domain.model.PartialPokemon

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToDetails: (Int) -> Unit
) {

    // collectAsStateWithLifecycle is the recommended standard over collectAsState() for Android, as it automatically stops collecting when the app is in the background, saving even more battery.
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = state.query,
            onValueChange = { viewModel.onQueryChanged(it) },
            label = { Text("Search Pokémon...") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(state.results) { partialPokemon ->
                    PokemonCard(
                        pokemon = partialPokemon,
                        onClick = { onNavigateToDetails(partialPokemon.id) })
                }
            }
        }
    }
}

@Composable
fun PokemonCard(pokemon: PartialPokemon, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = pokemon.name, style = MaterialTheme.typography.bodyMedium)
        }
    }
}