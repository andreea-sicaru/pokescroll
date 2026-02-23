package com.example.pokescroll.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokescroll.domain.model.PokemonStat

@Entity(tableName = "favourite_pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String,
    val stats: List<PokemonStat>, // Room needs a converter for this
    val isFavourite: Boolean
)