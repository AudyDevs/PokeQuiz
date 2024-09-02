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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokequiz.R
import com.example.pokequiz.core.type.TypeGame
import com.example.pokequiz.domain.state.PokemonState
import com.example.pokequiz.presentation.composable.Background
import com.example.pokequiz.presentation.composable.BouncingImage
import com.example.pokequiz.presentation.composable.CustomMenuButton
import com.example.pokequiz.presentation.composable.CustomSingleText
import com.example.pokequiz.presentation.composable.DialogGeneration
import com.example.pokequiz.presentation.composable.DialogNameTrainer
import com.example.pokequiz.presentation.composable.LoadingOptionMenu
import com.example.pokequiz.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navigateToGame: (typeGame: String, idGeneration: Int, nameTrainer: String) -> Unit,
    navigateToScore: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.readNameTrainer()
    viewModel.savePokemonList()
    val nameTrainer by viewModel.nameTrainer.collectAsState()
    val pokemonState by viewModel.pokemonListState.collectAsState()
    var showDialogGeneration by remember { mutableStateOf(false) }

    Background()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        CustomSingleText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            bodyText = stringResource(id = R.string.welcome),
            color = Color.Black,
            size = 16.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomSingleText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            bodyText = stringResource(id = R.string.trainer, nameTrainer),
            color = Color.Black,
            size = 16.sp
        )
        Spacer(modifier = Modifier.height(26.dp))
        BouncingImage(
            painter = painterResource(id = R.drawable.ic_poke_quiz),
            scaleImage = 100
        )
    }

    if (showDialogGeneration) {
        DialogGeneration(onDismiss = {
            showDialogGeneration = false
        }, onConfirm = { idGeneration ->
            showDialogGeneration = false
            navigateToGame(TypeGame.Training.typeGame, idGeneration, nameTrainer)
        })
    } else {
        Column(
            modifier = Modifier.fillMaxWidth(),
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
                    if (nameTrainer.isEmpty()) {
                        DialogNameTrainer(
                            onDismiss = {},
                            onConfirm = { name ->
                                viewModel.saveNameTrainer(name)
                            })
                    } else {
                        Spacer(modifier = Modifier.weight(0.75f))
                        OptionMenu(
                            selectedToGame = { typeGame ->
                                when (typeGame) {
                                    TypeGame.League -> {
                                        navigateToGame(typeGame.typeGame, 0, nameTrainer)
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
            }
            Spacer(modifier = Modifier.weight(0.85f))
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
        48.dp,
        48.dp,
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
        48.dp,
        48.dp,
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
        48.dp,
        48.dp,
        painterResource(id = R.drawable.ic_masterball),
        stringResource(id = R.string.hallOfFame)
    )
}