package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.FireStoreRepository
import javax.inject.Inject

class GetScorePointsUseCase @Inject constructor(private val fireStoreRepository: FireStoreRepository) {
    suspend operator fun invoke() = fireStoreRepository.getScorePoints()
}