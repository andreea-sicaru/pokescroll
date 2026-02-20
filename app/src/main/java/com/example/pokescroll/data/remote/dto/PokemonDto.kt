package com.example.pokescroll.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * We use DTOs to separate the API's messy structure from our clean Domain model.
 * If the API changes its JSON key names tomorrow, we only change the DTO and the Mapper, not our whole app logic.
 */
data class PokemonDto (
    val id: Int,
    val name: String,
    val stats: List<StatDto>,
    val sprites: SpritesDto
)

data class StatDto(
    @SerializedName("base_stat") val baseStat: Int,
    val stat: StatNameDto // Nested object
)

data class StatNameDto(
    val name: String // This is where "speed" or "hp" lives
)

data class SpritesDto(
    val other: OtherSpritesDto
)

data class OtherSpritesDto(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtworkDto
)

data class OfficialArtworkDto(
    @SerializedName("front_default") val frontDefault: String
)

data class NamedResourceDto(
    val name: String
)