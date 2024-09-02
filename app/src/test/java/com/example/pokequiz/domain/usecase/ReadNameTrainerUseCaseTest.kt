package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.DataStoreRepository
import com.example.pokequiz.motherobject.PokeQuizMotherObject.anyNameTrainer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ReadNameTrainerUseCaseTest {

    @MockK
    private lateinit var dataStoreRepository: DataStoreRepository
    private lateinit var readNameTrainerUseCase: ReadNameTrainerUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        readNameTrainerUseCase = ReadNameTrainerUseCase(dataStoreRepository)
    }

    @Test
    fun `when ReadNameTrainerUseCase is called successfully, DataStoreRepository should return a correct name of trainer`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readNameTrainer() } returns anyNameTrainer

            //When
            val nameTrainer = readNameTrainerUseCase.invoke()

            //Then
            assert(nameTrainer == anyNameTrainer)
        }

    @Test
    fun `when ReadNameTrainerUseCase is called successfully, DataStoreRepository should call readNameTrainer once`() =
        runBlocking {
            //Given
            coEvery { dataStoreRepository.readNameTrainer() } returns anyNameTrainer

            //When
            readNameTrainerUseCase.invoke()

            //Then
            coVerify(exactly = 1) { dataStoreRepository.readNameTrainer() }
        }
}