package com.example.pokequiz.presentation.viewmodel

import com.example.pokequiz.dispatcher.DispatcherRule
import com.example.pokequiz.dispatcher.TestDispatchers
import com.example.pokequiz.domain.usecase.GetPokemonListUseCase
import com.example.pokequiz.domain.usecase.SaveScorePointsUseCase
import com.example.pokequiz.motherobject.PokeQuizMotherObject
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyPokemonStateLoading
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyScoreModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameViewModelTest {

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @MockK
    private lateinit var getPokemonListUseCase: GetPokemonListUseCase

    @MockK
    private lateinit var saveScorePointsUseCase: SaveScorePointsUseCase

    private lateinit var gameViewModel: GameViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testDispatchers = TestDispatchers()
        gameViewModel = GameViewModel(
            testDispatchers,
            getPokemonListUseCase,
            saveScorePointsUseCase
        )
    }

    @Test
    fun `when GameViewModel calls getPokemonList successfully, should return a correct PokemonState`() =
        runBlocking {
            //Given
            val anyPokemonStateLoading = anyPokemonStateLoading
            coEvery { getPokemonListUseCase.invoke() } returns anyPokemonStateLoading

            //When
            val pokemonStateLoading = getPokemonListUseCase.invoke()

            //Then
            assert(pokemonStateLoading == anyPokemonStateLoading)
        }

    @Test
    fun `when GameViewModel calls getPokemonList successfully, it should call getPokemonListUseCase once`() =
        runBlocking {
            //Given
            coEvery { getPokemonListUseCase.invoke() } returns anyPokemonStateLoading

            //When
            gameViewModel.getPokemonList()
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { getPokemonListUseCase.invoke() }
        }

    @Test
    fun `when GameViewModel calls saveScorePoints successfully, it should call saveScorePointsUseCase once`() =
        runBlocking {
            //Given
            val anyScoreModel = anyScoreModel
            val anyTaskScoreSuccessState = PokeQuizMotherObject.anyTaskScoreSuccessState()
            coEvery { saveScorePointsUseCase.invoke(anyScoreModel) } returns anyTaskScoreSuccessState

            //When
            gameViewModel.saveScorePoints(anyScoreModel)
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { saveScorePointsUseCase.invoke(anyScoreModel) }
        }
}