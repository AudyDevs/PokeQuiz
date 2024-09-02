package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.DataStoreRepository
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyNameTrainer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class SaveNameTrainerUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var saveNameTrainerUseCase: SaveNameTrainerUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        saveNameTrainerUseCase = SaveNameTrainerUseCase(dataStoreRepository)
    }

    @Test
    fun `when SaveNameTrainerUseCase is called successfully, DataStoreRepository should call saveColumnsList once`() =
        runBlocking {
            //Given
            val anyNameTrainer = anyNameTrainer
            coEvery { dataStoreRepository.saveNameTrainer(anyNameTrainer) } just runs

            //When
            saveNameTrainerUseCase.invoke(anyNameTrainer)

            //Then
            coVerify(exactly = 1) { dataStoreRepository.saveNameTrainer(anyNameTrainer) }
        }
}