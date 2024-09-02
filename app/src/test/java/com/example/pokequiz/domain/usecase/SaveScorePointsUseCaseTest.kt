package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.FireStoreRepository
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyScoreModel
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyTaskScoreSuccessState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SaveScorePointsUseCaseTest {

    @MockK
    private lateinit var fireStoreRepository: FireStoreRepository
    private lateinit var saveScorePointsUseCase: SaveScorePointsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        saveScorePointsUseCase = SaveScorePointsUseCase(fireStoreRepository)
    }

    @Test
    fun `when SaveScorePointsUseCase is called successfully, FireStoreRepository should return a correct success ScoreState`() =
        runBlocking {
            //Given
            val scoreModel = anyScoreModel
            val anyTaskScoreSuccessState = anyTaskScoreSuccessState()
            coEvery { fireStoreRepository.saveScorePoints(scoreModel) } returns anyTaskScoreSuccessState

            //When
            val taskScoreState = saveScorePointsUseCase.invoke(scoreModel)

            //Then
            assert(taskScoreState == anyTaskScoreSuccessState)
        }

    @Test
    fun `when SaveScorePointsUseCase is called successfully, FireStoreRepository should call saveScorePoints once`() =
        runBlocking {
            //Given
            val scoreModel = anyScoreModel
            val anyTaskScoreSuccessState = anyTaskScoreSuccessState()
            coEvery { fireStoreRepository.saveScorePoints(scoreModel) } returns anyTaskScoreSuccessState

            //When
            saveScorePointsUseCase.invoke(scoreModel)

            //Then
            coVerify(exactly = 1) { fireStoreRepository.saveScorePoints(scoreModel) }
        }
}