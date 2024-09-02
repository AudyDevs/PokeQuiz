package com.example.pokequiz.domain.state

import com.example.pokequiz.domain.model.ScoreModel

sealed class ScoreState {
    data object Loading : ScoreState()
    data class Success(val score: List<ScoreModel>) : ScoreState()
    data class Error(val error: String) : ScoreState()
}