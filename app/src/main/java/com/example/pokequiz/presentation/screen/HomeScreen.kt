package com.example.pokequiz.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokequiz.R
import com.example.pokequiz.core.TypeGame
import com.example.pokequiz.domain.state.PokemonState
import com.example.pokequiz.presentation.composable.Background
import com.example.pokequiz.presentation.composable.BouncingImage
import com.example.pokequiz.presentation.composable.CustomMenuButton
import com.example.pokequiz.presentation.composable.DialogGeneration
import com.example.pokequiz.presentation.composable.LoadingOptionMenu
import com.example.pokequiz.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navigateToGame: (typeGame: String, idGeneration: Int) -> Unit,
    navigateToScore: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.savePokemonList()
    val pokemonState by viewModel.pokemonListState.collectAsState()
    var showDialogGeneration by remember { mutableStateOf(false) }

    Background()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        BouncingImage(
            painter = painterResource(id = R.drawable.ic_poke_quiz)
        )
    }

    if (showDialogGeneration) {
        DialogGeneration(onDismiss = {
            showDialogGeneration = false
        }, onConfirm = { idGeneration ->
            showDialogGeneration = false
            navigateToGame(TypeGame.Training.typeGame, idGeneration)
        })
    } else {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))
            when (pokemonState) {
                PokemonState.Error -> {}
                PokemonState.Loading -> {
                    LoadingOptionMenu()
                }

                is PokemonState.Success -> {
                    Spacer(modifier = Modifier.weight(0.75f))
                    OptionMenu(
                        selectedToGame = { typeGame ->
                            when (typeGame) {
                                TypeGame.League -> {
                                    navigateToGame(typeGame.typeGame, 0)
                                }

                                TypeGame.Training -> {
                                    showDialogGeneration = true
                                }
                            }
                        },
                        selectedToScore = {
                            navigateToScore()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun OptionMenu(
    selectedToGame: (typeGame: TypeGame) -> Unit,
    selectedToScore: () -> Unit,
) {
    CustomMenuButton(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                selectedToGame(TypeGame.League)
            },
        painterResource(id = R.drawable.ic_pokeball),
        stringResource(id = R.string.pokemonLeague)
    )
    Spacer(modifier = Modifier.height(16.dp))
    CustomMenuButton(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                selectedToGame(TypeGame.Training)
            },
        painterResource(id = R.drawable.ic_superball),
        stringResource(id = R.string.training)
    )
    Spacer(modifier = Modifier.height(16.dp))
    CustomMenuButton(
        modifier = Modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                selectedToScore()
            },
        painterResource(id = R.drawable.ic_masterball),
        stringResource(id = R.string.hallOfFame)
    )
}