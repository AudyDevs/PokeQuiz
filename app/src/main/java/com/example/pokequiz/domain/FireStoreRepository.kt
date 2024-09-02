package com.example.pokequiz.domain

import com.example.pokequiz.domain.model.ScoreModel
import com.example.pokequiz.domain.state.ScoreState
import com.google.android.gms.tasks.Task

interface FireStoreRepository {

    suspend fun getScorePoints(): Task<ScoreState>

    suspend fun saveScorePoints(scoreModel: ScoreModel): Task<ScoreState>
}