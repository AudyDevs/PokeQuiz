package com.example.pokequiz.motherobject

import com.example.pokequiz.data.room.entities.PokemonEntity
import com.example.pokequiz.domain.model.PokemonModel
import com.example.pokequiz.domain.model.ScoreModel
import com.example.pokequiz.domain.state.PokemonState
import com.example.pokequiz.domain.state.ScoreState
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.TaskCompletionSource

object PokeQuizMotherObject {
    val anyPokemonModel = PokemonModel(1, "nameTest", "imageTest")
    val anyPokemonEntity = PokemonEntity(1, "nameTest", "imageTest")

    val anyListPokemonEntity = listOf(anyPokemonEntity, anyPokemonEntity)

    const val anyNameTrainer = "NameTrainerTest"
    val anyPokemonStateLoading = PokemonState.Loading

    val anyScoreModel = ScoreModel(1, "NameTrainerTest", 1000)
    private val anyListScoreModel = listOf(anyScoreModel)

    private fun anyScoreStateSuccess() = ScoreState.Success(anyListScoreModel)

    fun anyTaskScoreSuccessState(): Task<ScoreState> {
        val taskCompletionSource = TaskCompletionSource<ScoreState>()
        val scoreState = anyScoreStateSuccess()
        taskCompletionSource.setResult(scoreState)
        return taskCompletionSource.task
    }
}