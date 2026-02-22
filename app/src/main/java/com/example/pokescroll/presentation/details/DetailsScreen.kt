package com.example.pokescroll.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(
    pokemonId: Int,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    // Manually trigger the fetch when the screen opens
    LaunchedEffect(pokemonId) {
        viewModel.getDetails(pokemonId)
    }

    val state = viewModel.state.collectAsState().value

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        state.error?.let { msg ->
            Text(text = msg, color = Color.Red, modifier = Modifier.align(Alignment.Center))
        }

        state.pokemon?.let { pokemon ->

            Column(modifier = Modifier.fillMaxSize()) {
                // Hero Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .background(Color.LightGray)
                ) {
                    AsyncImage(
                        model = pokemon.imageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(200.dp)
                    )
                }

                // Stats Section
                Text(text = pokemon.name, style = MaterialTheme.typography.headlineLarge)
                pokemon.stats.forEach { stat ->
                    Text("${stat.name}: ${stat.value}")
                }
            }
        }
    }
}