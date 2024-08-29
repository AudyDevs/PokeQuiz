package com.example.pokequiz.domain.model

data class PokemonModel(
    val id: Int,
    val name: String,
    val imageBig: String,
    var optionsPokemon: List<String> = emptyList()
)