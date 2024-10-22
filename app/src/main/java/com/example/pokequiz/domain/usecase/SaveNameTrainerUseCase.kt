package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.DataStoreRepository
import javax.inject.Inject

class SaveNameTrainerUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(nameTrainer: String) =
        dataStoreRepository.saveNameTrainer(nameTrainer)
}