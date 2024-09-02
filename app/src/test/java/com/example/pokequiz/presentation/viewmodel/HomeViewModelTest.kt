package com.example.pokequiz.presentation.viewmodel

import com.example.pokequiz.dispatcher.DispatcherRule
import com.example.pokequiz.dispatcher.TestDispatchers
import com.example.pokequiz.domain.usecase.ReadNameTrainerUseCase
import com.example.pokequiz.domain.usecase.SaveNameTrainerUseCase
import com.example.pokequiz.domain.usecase.SavePokemonListUseCase
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyNameTrainer
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyPokemonStateLoading
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @MockK
    private lateinit var savePokemonListUseCase: SavePokemonListUseCase

    @MockK
    private lateinit var readNameTrainerUseCase: ReadNameTrainerUseCase

    @MockK
    private lateinit var saveNameTrainerUseCase: SaveNameTrainerUseCase

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testDispatchers = TestDispatchers()
        homeViewModel = HomeViewModel(
            testDispatchers,
            savePokemonListUseCase,
            readNameTrainerUseCase,
            saveNameTrainerUseCase
        )
    }

    @Test
    fun `when HomeViewModel calls savePokemonList successfully, should return a correct PokemonState`() =
        runBlocking {
            //Given
            val anyPokemonStateLoading = anyPokemonStateLoading
            coEvery { savePokemonListUseCase.invoke() } returns anyPokemonStateLoading

            //When
            val pokemonStateLoading = savePokemonListUseCase.invoke()

            //Then
            assert(pokemonStateLoading == anyPokemonStateLoading)
        }

    @Test
    fun `when HomeViewModel calls savePokemonList successfully, it should call savePokemonListUseCase once`() =
        runBlocking {
            //Given
            coEvery { savePokemonListUseCase.invoke() } returns anyPokemonStateLoading

            //When
            homeViewModel.savePokemonList()
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { savePokemonListUseCase.invoke() }
        }

    @Test
    fun `when HomeViewModel calls readNameTrainer successfully, should return a correct name of trainer`() =
        runBlocking {
            //Given
            val anyNameTrainer = anyNameTrainer
            coEvery { readNameTrainerUseCase.invoke() } returns anyNameTrainer

            //When
            val nameTrainer = readNameTrainerUseCase.invoke()

            //Then
            assert(nameTrainer == anyNameTrainer)
        }

    @Test
    fun `when HomeViewModel calls readNameTrainer successfully, it should call readNameTrainerUseCase once`() =
        runBlocking {
            //Given
            coEvery { readNameTrainerUseCase.invoke() } returns anyNameTrainer

            //When
            homeViewModel.readNameTrainer()
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { readNameTrainerUseCase.invoke() }
        }

    @Test
    fun `when HomeViewModel calls saveNameTrainer successfully, it should call saveNameTrainerUseCase once`() =
        runBlocking {
            //Given
            val anyNameTrainer = anyNameTrainer
            coEvery { saveNameTrainerUseCase.invoke(anyNameTrainer) } just runs

            //When
            homeViewModel.saveNameTrainer(anyNameTrainer)
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { saveNameTrainerUseCase.invoke(anyNameTrainer) }
        }
}