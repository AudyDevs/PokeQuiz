package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.PokemonRepository
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyPokemonStateLoading
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetPokemonListUseCaseTest {

    @MockK
    private lateinit var pokemonRepository: PokemonRepository
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getPokemonListUseCase = GetPokemonListUseCase(pokemonRepository)
    }

    @Test
    fun `when GetPokemonListUseCase is called successfully, PokemonRepository should return a correct list of pokemon`() =
        runBlocking {
            //Given
            val anyPokemonStateLoading = anyPokemonStateLoading
            coEvery { pokemonRepository.getPokemonListFromRoom() } returns anyPokemonStateLoading

            //When
            val pokemonListLoading = getPokemonListUseCase.invoke()

            //Then
            assert(pokemonListLoading == anyPokemonStateLoading)
        }

    @Test
    fun `when GetPokemonListUseCase is called successfully, PokemonRepository should call getPokemonListFromRoom once`() =
        runBlocking {
            //Given
            val anyPokemonStateLoading = anyPokemonStateLoading
            coEvery { pokemonRepository.getPokemonListFromRoom() } returns anyPokemonStateLoading

            //When
            getPokemonListUseCase.invoke()

            //Then
            coVerify(exactly = 1) { pokemonRepository.getPokemonListFromRoom() }
        }
}