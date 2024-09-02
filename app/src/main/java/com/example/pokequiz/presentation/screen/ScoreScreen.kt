package com.example.pokequiz.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokequiz.R
import com.example.pokequiz.domain.model.ScoreModel
import com.example.pokequiz.domain.state.ScoreState
import com.example.pokequiz.presentation.composable.Background
import com.example.pokequiz.presentation.composable.BouncingImage
import com.example.pokequiz.presentation.composable.CustomSingleText
import com.example.pokequiz.presentation.ui.theme.BlueSemiTransparent
import com.example.pokequiz.presentation.ui.theme.CopperSemiTransparent
import com.example.pokequiz.presentation.ui.theme.GoldSemiTransparent
import com.example.pokequiz.presentation.ui.theme.SilverSemiTransparent
import com.example.pokequiz.presentation.viewmodel.ScoreViewModel

@Composable
fun ScoreScreen(
    viewModel: ScoreViewModel = hiltViewModel()
) {
    val pointsState by viewModel.pointsState.collectAsState()

    Background()
    when (pointsState) {
        is ScoreState.Error -> {}
        ScoreState.Loading -> {
            LoadingGame()
        }

        is ScoreState.Success -> {
            ScoreList((pointsState as ScoreState.Success).score)
        }
    }
}

@Composable
fun ScoreList(scoreList: List<ScoreModel>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(36.dp))
        BouncingImage(
            painter = painterResource(id = R.drawable.ic_hall_of_fame),
            scaleImage = 65
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 160.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val scoreListSorted = scoreList.sortedByDescending { it.totalPoints }
        var order = 0
        scoreListSorted.forEach {
            order++
            it.order = order
        }
        items(scoreListSorted) { score ->
            ScoreRow(score)
        }
    }
}

@Composable
fun ScoreRow(score: ScoreModel) {
    val colorBackgroundBox = when (score.order) {
        1 -> {
            GoldSemiTransparent
        }

        2 -> {
            SilverSemiTransparent
        }

        3 -> {
            CopperSemiTransparent
        }

        else -> {
            BlueSemiTransparent
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .background(colorBackgroundBox, shape = RoundedCornerShape(8.dp))
    ) {
        CustomSingleText(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
                .wrapContentWidth(Alignment.Start),
            bodyText = score.order.toString(),
            color = Color.Black,
            size = 12.sp
        )
        CustomSingleText(
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentWidth(Alignment.CenterHorizontally),
            bodyText = score.nameTrainer!!,
            color = Color.Black,
            size = 12.sp
        )
        CustomSingleText(
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
                .wrapContentWidth(Alignment.End),
            bodyText = score.totalPoints.toString(),
            color = Color.Black,
            size = 12.sp
        )
    }
}