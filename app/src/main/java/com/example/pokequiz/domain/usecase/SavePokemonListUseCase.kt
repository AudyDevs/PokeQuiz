package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.PokemonRepository
import javax.inject.Inject

class SavePokemonListUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke() = pokemonRepository.savePokemonList()
}