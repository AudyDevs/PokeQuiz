package com.example.pokequiz.domain.usecase

import com.example.pokequiz.domain.FireStoreRepository
import com.example.pokequiz.domain.model.ScoreModel
import javax.inject.Inject

class SaveScorePointsUseCase @Inject constructor(private val fireStoreRepository: FireStoreRepository) {
    suspend operator fun invoke(scoreModel: ScoreModel) =
        fireStoreRepository.saveScorePoints(scoreModel)
}