package com.example.pokequiz.data

import com.example.pokequiz.core.datastore.DataStorePreferences
import com.example.pokequiz.domain.DataStoreRepository
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStorePreferences: DataStorePreferences
) : DataStoreRepository {

    override suspend fun readNameTrainer(): String {
        return dataStorePreferences.readDataStoreNameTrainer()
    }

    override suspend fun saveNameTrainer(nameTrainer: String) {
        dataStorePreferences.saveDataStoreNameTrainer(nameTrainer)
    }
}