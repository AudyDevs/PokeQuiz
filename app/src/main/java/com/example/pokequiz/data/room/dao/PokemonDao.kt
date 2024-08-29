package com.example.pokequiz.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokequiz.data.room.entities.PokemonEntity

@Dao
interface PokemonDao {

    // Pokemon
    @Query("SELECT * FROM pokemon ORDER BY id ASC")
    suspend fun getPokemon(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonList: List<PokemonEntity>)

    @Query("DELETE FROM pokemon")
    suspend fun clearPokemon()
}