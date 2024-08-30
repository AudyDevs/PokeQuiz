package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokequiz.R
import com.example.pokequiz.presentation.ui.theme.Blue
import com.example.pokequiz.presentation.ui.theme.Yellow

@Composable
fun CustomMenuButton(
    modifier: Modifier,
    height: Dp,
    padding: Dp,
    painter: Painter,
    titleButton: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = padding)
            .background(Yellow, CircleShape)
            .border(2.dp, Blue, CircleShape), contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.cd_pokeball),
            modifier = modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
                .size(18.dp)
        )
        CustomSingleText(modifier, titleButton, Blue, 12.sp)
        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.cd_pokeball),
            modifier = modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
                .size(18.dp)
        )
    }
}