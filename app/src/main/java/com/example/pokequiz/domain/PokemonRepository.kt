package com.example.pokequiz.domain

import com.example.pokequiz.domain.state.PokemonState

interface PokemonRepository {

    suspend fun savePokemonList(): PokemonState

    suspend fun getPokemonListFromRoom(): PokemonState
}