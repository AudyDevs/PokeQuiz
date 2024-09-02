package com.example.pokequiz.core.mappers

import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyPokemonEntity
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyPokemonModel
import io.kotlintest.shouldBe
import org.junit.Test

class PokemonMapKtTest {

    @Test
    fun `toRoom should return a correct PokemonEntity`() {
        //Given
        val pokemonModel = anyPokemonModel

        //When
        val pokemonEntity = pokemonModel.toRoom()

        //Then
        pokemonEntity.id shouldBe pokemonModel.id
        pokemonEntity.name shouldBe pokemonModel.name
        pokemonEntity.imageBig shouldBe pokemonModel.imageBig
    }

    @Test
    fun `toDomain should return a correct PokemonModel`() {
        //Given
        val pokemonEntity = anyPokemonEntity

        //When
        val pokemonModel = pokemonEntity.toDomain()

        //Then
        pokemonModel.id shouldBe pokemonEntity.id
        pokemonModel.name shouldBe pokemonEntity.name
        pokemonModel.imageBig shouldBe pokemonEntity.imageBig
    }
}