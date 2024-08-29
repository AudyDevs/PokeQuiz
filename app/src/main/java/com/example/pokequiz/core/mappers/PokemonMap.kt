package com.example.pokequiz.core.mappers

import com.example.pokequiz.data.room.entities.PokemonEntity
import com.example.pokequiz.domain.model.PokemonModel

fun PokemonEntity.toDomain() = PokemonModel(
    id = id,
    name = name,
    imageBig = imageBig
)

fun PokemonModel.toRoom() = PokemonEntity(
    id = id,
    name = name,
    imageBig = imageBig
)