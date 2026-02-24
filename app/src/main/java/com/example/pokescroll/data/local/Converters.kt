package com.example.pokescroll.data.local

import androidx.room.TypeConverter
import com.example.pokescroll.domain.model.PokemonStat
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Room doesn't know how to store that "list" in a single SQL column. We need a TypeConverter to turn that list into a JSON string and back.
 */
class Converters {

    @TypeConverter
    fun fromStatList(value: List<PokemonStat>): String = Json.encodeToString(value)

    @TypeConverter
    fun toStatList(value: String): List<PokemonStat> = Json.decodeFromString(value)
}