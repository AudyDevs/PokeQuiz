package com.example.pokequiz.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataStorePreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        const val PREFERENCE_KEY_USER = "PK_POKE_QUIZ_CONFIG"
        const val NAME_TRAINER = "NAME_TRAINER"
    }

    private object PreferenceKeys {
        val nameTrainer = stringPreferencesKey(NAME_TRAINER)
    }

    // Name Trainer
    suspend fun readDataStoreNameTrainer(): String = runBlocking {
        val preferences = dataStore.data.first()
        return@runBlocking preferences[PreferenceKeys.nameTrainer].orEmpty()
    }

    suspend fun saveDataStoreNameTrainer(nameTrainer: String) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.nameTrainer] = nameTrainer
        }
    }
}