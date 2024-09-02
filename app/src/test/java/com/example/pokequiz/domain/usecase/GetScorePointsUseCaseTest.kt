package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.FireStoreRepository
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyTaskScoreSuccessState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetScorePointsUseCaseTest {

    @MockK
    private lateinit var fireStoreRepository: FireStoreRepository
    private lateinit var getScorePointsUseCase: GetScorePointsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getScorePointsUseCase = GetScorePointsUseCase(fireStoreRepository)
    }

    @Test
    fun `when GetScorePointsUseCase is called successfully, FireStoreRepository should return a correct list of score points`() =
        runBlocking {
            //Given
            val anyTaskScoreSuccessState = anyTaskScoreSuccessState()
            coEvery { fireStoreRepository.getScorePoints() } returns anyTaskScoreSuccessState

            //When
            val taskScoreState = getScorePointsUseCase.invoke()

            //Then
            assert(taskScoreState == anyTaskScoreSuccessState)
        }

    @Test
    fun `when GetScorePointsUseCase is called successfully, FireStoreRepository should call getScorePoints once`() =
        runBlocking {
            //Given
            val anyTaskScoreSuccessState = anyTaskScoreSuccessState()
            coEvery { fireStoreRepository.getScorePoints() } returns anyTaskScoreSuccessState

            //When
            getScorePointsUseCase.invoke()

            //Then
            coVerify(exactly = 1) { fireStoreRepository.getScorePoints() }
        }
}