package com.example.pokequiz.data

import com.example.pokequiz.core.datastore.DataStorePreferences
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

class DataStoreRepositoryImplTest {

    @MockK
    private lateinit var dataStorePreferences: DataStorePreferences
    private lateinit var dataStoreRepositoryImpl: DataStoreRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dataStoreRepositoryImpl = DataStoreRepositoryImpl(dataStorePreferences)
    }

    @Test
    fun `when DataStoreRepositoryImpl call readNameTrainer successfully, it should return a correct name of trainer`() =
        runBlocking {
            //Given
            val anyNameTrainer = anyNameTrainer
            coEvery { dataStorePreferences.readDataStoreNameTrainer() } returns anyNameTrainer

            //When
            val nameTrainer = dataStoreRepositoryImpl.readNameTrainer()

            //Then
            assert(nameTrainer == anyNameTrainer)
        }


    @Test
    fun `when DataStoreRepositoryImpl call readNameTrainer successfully, it should call readDataStoreNameTrainer from DataStorePreferences once`() =
        runBlocking {
            //Given
            val anyNameTrainer = anyNameTrainer
            coEvery { dataStorePreferences.readDataStoreNameTrainer() } returns anyNameTrainer

            //When
            dataStoreRepositoryImpl.readNameTrainer()

            //Then
            coVerify(exactly = 1) { dataStorePreferences.readDataStoreNameTrainer() }
        }

    @Test
    fun `when DataStoreRepositoryImpl call saveNameTrainer successfully, it should call saveDataStoreNameTrainer from DataStorePreferences once`() =
        runBlocking {
            //Given
            val anyNameTrainer = anyNameTrainer
            coEvery { dataStorePreferences.saveDataStoreNameTrainer(anyNameTrainer) } just runs

            //When
            dataStoreRepositoryImpl.saveNameTrainer(anyNameTrainer)

            //Then
            coVerify(exactly = 1) { dataStorePreferences.saveDataStoreNameTrainer(anyNameTrainer) }
        }
}