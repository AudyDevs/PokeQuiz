package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.example.pokequiz.core.Constants.MAX_TIME_GAME
import com.example.pokequiz.presentation.ui.theme.Blue
import com.example.pokequiz.presentation.ui.theme.Yellow
import com.example.pokequiz.presentation.viewmodel.GameViewModel
import kotlinx.coroutines.delay

@Composable
fun ProgressTimeBar(viewModel: GameViewModel, onTimeUp: () -> Unit) {
    val progress by viewModel.progress.collectAsState()
    val waitingTime by viewModel.waitingTime.collectAsState()
    val isRunningGame by viewModel.isRunningGame.collectAsState()

    LaunchedEffect(isRunningGame) {
        if (isRunningGame) {
            val durationMillis = MAX_TIME_GAME
            val durationStep = 0.1f
            while (isRunningGame && waitingTime < durationMillis) {
                viewModel.saveWaitingTime(waitingTime + durationStep)
                viewModel.saveProgressStep(1f - (waitingTime / durationMillis))
                viewModel.savePointsStep(durationMillis - waitingTime)
                delay(100)
            }
            if (waitingTime >= durationMillis) {
                onTimeUp()
            }
        }
    }

    LinearProgressIndicator(
        progress = progress,
        color = Yellow,
        trackColor = Blue,
        strokeCap = StrokeCap.Round,
        modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
            .height(24.dp)
            .border(1.5.dp, Color.Black, CircleShape)
    )
}