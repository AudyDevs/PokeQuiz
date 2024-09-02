package com.example.pokequiz.presentation.viewmodel

import com.example.pokequiz.dispatcher.DispatcherRule
import com.example.pokequiz.dispatcher.TestDispatchers
import com.example.pokequiz.domain.usecase.GetScorePointsUseCase
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyTaskScoreSuccessState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ScoreViewModelTest {

    @get:Rule
    val dispatcherRule = DispatcherRule()

    @MockK
    private lateinit var getScorePointsUseCase: GetScorePointsUseCase

    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var testDispatchers: TestDispatchers

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        testDispatchers = TestDispatchers()
        scoreViewModel = ScoreViewModel(
            testDispatchers,
            getScorePointsUseCase
        )
    }

    @Test
    fun `when ScoreViewModel is created successfully, it should calls getScorePointsUseCase each once`() =
        runBlocking {
            //Given
            val anyTaskScoreSuccessState = anyTaskScoreSuccessState()
            coEvery { getScorePointsUseCase.invoke() } returns anyTaskScoreSuccessState

            //When
            testDispatchers.testDispatchers.scheduler.advanceUntilIdle()

            //Then
            coVerify(exactly = 1) { getScorePointsUseCase.invoke() }
        }
}