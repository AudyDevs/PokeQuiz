package com.example.pokequiz.domain

interface DataStoreRepository {

    suspend fun readNameTrainer(): String

    suspend fun saveNameTrainer(nameTrainer: String)
}