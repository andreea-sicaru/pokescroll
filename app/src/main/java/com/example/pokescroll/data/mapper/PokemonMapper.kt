package com.example.pokescroll.data.mapper

import com.example.pokescroll.data.remote.dto.PartialPokemonDto
import com.example.pokescroll.data.remote.dto.PokemonDto
import com.example.pokescroll.domain.model.PartialPokemon
import com.example.pokescroll.domain.model.Pokemon
import com.example.pokescroll.domain.model.PokemonStat

fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() }, // Clean up the name
        imageUrl = sprites.other.officialArtwork.frontDefault,
        stats = stats.map {
            PokemonStat(name = it.stat.name, value = it.baseStat)
        }
    )
}

fun PartialPokemonDto.toDomain(): PartialPokemon {
    return PartialPokemon(
        id = url.trimEnd('/').split('/').last().toInt(),
        name = name
    )
}