package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pokequiz.R
import com.example.pokequiz.core.Constants.MAX_POKEMON_GAME
import com.example.pokequiz.core.Constants.MAX_TIME_GAME
import com.example.pokequiz.core.type.Generations
import com.example.pokequiz.core.type.TypeGame
import com.example.pokequiz.presentation.ui.theme.Blue

@Composable
fun DialogTotalScore(
    typeGame: String,
    idGeneration: Int,
    totalPoints: Int,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    var title = ""
    var body = ""
    if (TypeGame.League.typeGame == typeGame) {
        title = stringResource(id = R.string.score_league_title)
        body = stringResource(id = R.string.score_league_body)
    } else if (TypeGame.Training.typeGame == typeGame) {
        val nameRegion =
            stringResource(id = Generations.entries.find { it.id == idGeneration }?.nameRegion!!)
        val averagePoints = (MAX_TIME_GAME * MAX_POKEMON_GAME) * 10 / 2
        title = stringResource(id = R.string.score_training_title, nameRegion)
        body = if (totalPoints > averagePoints) {
            stringResource(id = R.string.score_training_body_big_points)
        } else {
            stringResource(id = R.string.score_training_body_low_points)
        }
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp), color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomMultiText(
                    modifier = Modifier,
                    bodyText = title,
                    color = Color.Black,
                    size = 11.sp
                )
                Spacer(modifier = Modifier.height(26.dp))
                CustomSingleText(
                    modifier = Modifier.fillMaxWidth(),
                    bodyText = stringResource(id = R.string.score_points, totalPoints),
                    color = Blue,
                    size = 18.sp
                )
                Spacer(modifier = Modifier.height(26.dp))
                CustomMultiText(
                    modifier = Modifier,
                    bodyText = body,
                    color = Color.Black,
                    size = 11.sp
                )
                Spacer(modifier = Modifier.height(18.dp))
                CustomMenuButton(
                    modifier = Modifier.clickable {
                        onConfirm()
                    },
                    40.dp,
                    8.dp,
                    painter = painterResource(id = R.drawable.ic_masterball),
                    titleButton = stringResource(
                        id = R.string.hallOfFame
                    )
                )
            }
        }
    }
}