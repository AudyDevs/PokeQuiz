package com.example.pokequiz.domain.state

import com.example.pokequiz.domain.model.PokemonModel

sealed class PokemonState {
    data object Loading : PokemonState()
    data class Success(var pokemon: List<PokemonModel>) : PokemonState()
    data object Error : PokemonState()
}