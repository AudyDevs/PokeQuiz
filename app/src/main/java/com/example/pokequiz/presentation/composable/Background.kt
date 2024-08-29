package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.pokequiz.R

@Composable
fun Background() {
    Image(
        painter = painterResource(id = R.drawable.ic_background),
        contentDescription = stringResource(id = R.string.cd_background),
        modifier = Modifier
            .fillMaxSize()
            .scale(1.4f)
    )
}