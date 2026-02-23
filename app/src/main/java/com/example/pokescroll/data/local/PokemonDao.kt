package com.example.pokescroll.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * We always use Flow for reading data so the UI updates automatically when a favourite is added or removed.
 */
@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(pokemon: PokemonEntity)

    @Query("DELETE FROM favourite_pokemon WHERE id = :id")
    suspend fun deleteFavourite(id: Int)

    @Query("SELECT * FROM favourite_pokemon")
    fun getAllFavourites(): Flow<List<PokemonEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favourite_pokemon WHERE id = :id)")
    fun isFavourite(id: Int): Flow<Boolean>
}