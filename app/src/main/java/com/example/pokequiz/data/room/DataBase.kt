package com.example.pokequiz.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokequiz.data.room.dao.PokemonDao
import com.example.pokequiz.data.room.entities.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun loadPokemonDao(): PokemonDao
}