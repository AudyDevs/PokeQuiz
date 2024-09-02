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

class SavePokemonListUseCaseTest {

    @MockK
    private lateinit var pokemonRepository: PokemonRepository
    private lateinit var savePokemonListUseCase: SavePokemonListUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        savePokemonListUseCase = SavePokemonListUseCase(pokemonRepository)
    }

    @Test
    fun `when SavePokemonListUseCase is called successfully, PokemonRepository should save a correct list of pokemon`() =
        runBlocking {
            //Given
            val anyPokemonStateLoading = anyPokemonStateLoading
            coEvery { pokemonRepository.savePokemonList() } returns anyPokemonStateLoading

            //When
            val pokemonListLoading = savePokemonListUseCase.invoke()

            //Then
            assert(pokemonListLoading == anyPokemonStateLoading)
        }

    @Test
    fun `when SavePokemonListUseCase is called successfully, PokemonRepository should call savePokemonList once`() =
        runBlocking {
            //Given
            val anyPokemonStateLoading = anyPokemonStateLoading
            coEvery { pokemonRepository.savePokemonList() } returns anyPokemonStateLoading

            //When
            savePokemonListUseCase.invoke()

            //Then
            coVerify(exactly = 1) { pokemonRepository.savePokemonList() }
        }
}