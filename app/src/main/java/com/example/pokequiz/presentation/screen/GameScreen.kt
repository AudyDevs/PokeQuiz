package com.example.pokequiz.presentation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokequiz.core.TypeGame
import com.example.pokequiz.core.extensions.formatNamePokemon
import com.example.pokequiz.domain.model.PokemonModel
import com.example.pokequiz.domain.state.PokemonState
import com.example.pokequiz.presentation.composable.Background
import com.example.pokequiz.presentation.composable.CustomSingleText
import com.example.pokequiz.presentation.composable.DialogTotalScore
import com.example.pokequiz.presentation.composable.LoadingOptionMenu
import com.example.pokequiz.presentation.composable.ProgressTimeBar
import com.example.pokequiz.presentation.composable.TogglePokemonImage
import com.example.pokequiz.presentation.ui.theme.GreenCorrectAnswer
import com.example.pokequiz.presentation.ui.theme.RedIncorrectAnswer
import com.example.pokequiz.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen(
    typeGame: String,
    idGeneration: Int,
    navigateToScore: () -> Unit,
    viewModel: GameViewModel = hiltViewModel()
) {
    val pokemonState by viewModel.pokemonListState.collectAsState()

    Background()
    when (pokemonState) {
        PokemonState.Error -> {}
        PokemonState.Loading -> {
            viewModel.getPokemonList()
            LoadingGame()
        }

        is PokemonState.Success -> {
            val listPokemonSelected =
                viewModel.getRandomPokemonForGame(
                    (pokemonState as PokemonState.Success).pokemon,
                    idGeneration
                )
            StartGame(
                listPokemonSelected = listPokemonSelected,
                typeGame = typeGame,
                idGeneration = idGeneration,
                viewModel = viewModel,
                navigateToScore = { navigateToScore() })
        }
    }
}

@Composable
fun LoadingGame() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        LoadingOptionMenu()
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun StartGame(
    listPokemonSelected: List<PokemonModel>,
    typeGame: String,
    idGeneration: Int,
    viewModel: GameViewModel,
    navigateToScore: () -> Unit
) {
    val isRunningGame by viewModel.isRunningGame.collectAsState()
    val selectedAnswer by viewModel.selectedAnswer.collectAsState()
    val positionGame by viewModel.positionGame.collectAsState()
    val showTotalPoints by viewModel.showTotalPoints.collectAsState()
    val totalPoints by viewModel.totalPoints.collectAsState()

    val pokemonSelected = listPokemonSelected[positionGame]
    val listPokemonGame: MutableList<String> = pokemonSelected.optionsPokemon.toMutableList()
    val positionCorrectAnswer: Int =
        listPokemonGame.indexOf(pokemonSelected.name.formatNamePokemon())

    if (showTotalPoints) {
        val roundedTotalPoints = (totalPoints * 10).toInt()
        DialogTotalScore(
            typeGame = typeGame,
            idGeneration = idGeneration,
            totalPoints = roundedTotalPoints,
            onConfirm = {
                if (TypeGame.League.typeGame == typeGame) {
                    viewModel.saveTotalGamePoints(roundedTotalPoints)
                }
                navigateToScore()
            },
            onDismiss = {})
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            ProgressTimeBar(
                viewModel,
                onTimeUp = {
                    viewModel.stopGameTimer(
                        running = false,
                        selectedAnswer = -1,
                        correctAnswer = false
                    )
                })
            Spacer(modifier = Modifier.weight(1f))
            TogglePokemonImage(isRunningGame, pokemonSelected.imageBig)
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OptionButtons(
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel,
                    positionCorrectAnswer = positionCorrectAnswer,
                    listPokemonGame = listPokemonGame,
                    idPosition = 0,
                    isRunningGame = isRunningGame,
                    selectedAnswer = selectedAnswer
                )
                OptionButtons(
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel,
                    positionCorrectAnswer = positionCorrectAnswer,
                    listPokemonGame = listPokemonGame,
                    idPosition = 1,
                    isRunningGame = isRunningGame,
                    selectedAnswer = selectedAnswer
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OptionButtons(
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel,
                    positionCorrectAnswer = positionCorrectAnswer,
                    listPokemonGame = listPokemonGame,
                    idPosition = 2,
                    isRunningGame = isRunningGame,
                    selectedAnswer = selectedAnswer
                )
                OptionButtons(
                    modifier = Modifier.weight(1f),
                    viewModel = viewModel,
                    positionCorrectAnswer = positionCorrectAnswer,
                    listPokemonGame = listPokemonGame,
                    idPosition = 3,
                    isRunningGame = isRunningGame,
                    selectedAnswer = selectedAnswer
                )
            }
            Spacer(modifier = Modifier.weight(1.35f))
        }
    }
}

@Composable
fun OptionButtons(
    modifier: Modifier,
    viewModel: GameViewModel,
    positionCorrectAnswer: Int,
    listPokemonGame: MutableList<String>,
    idPosition: Int,
    isRunningGame: Boolean,
    selectedAnswer: Int
) {
    val correctAnswer = (positionCorrectAnswer == idPosition)
    val containerColor = if (!isRunningGame) {
        if (correctAnswer) {
            GreenCorrectAnswer
        } else if (selectedAnswer == idPosition) {
            RedIncorrectAnswer
        } else {
            Color.DarkGray
        }
    } else {
        Color.DarkGray
    }
    Button(
        onClick = {
            viewModel.stopGameTimer(
                running = false,
                selectedAnswer = idPosition,
                correctAnswer = correctAnswer
            )
        },
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        border = BorderStroke(width = 2.dp, color = Color.Black),
        modifier = modifier
    ) {
        CustomSingleText(Modifier, listPokemonGame[idPosition], Color.White, 10.sp)
    }
}