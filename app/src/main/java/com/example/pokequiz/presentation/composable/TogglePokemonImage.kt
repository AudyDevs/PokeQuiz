package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pokequiz.R

@Composable
fun TogglePokemonImage(isRunningGame: Boolean, painter: String) {
    if (isRunningGame) {
        AsyncImage(
            model = painter,
            contentDescription = stringResource(id = R.string.cd_pokemon),
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier
                .size(260.dp)
        )
    } else {
        AsyncImage(
            model = painter,
            contentDescription = stringResource(id = R.string.cd_pokemon),
            modifier = Modifier
                .size(260.dp)
        )
    }
}