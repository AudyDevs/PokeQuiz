package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokequiz.R
import com.example.pokequiz.core.type.Generations

@Composable
fun CustomGenerationButton(
    modifier: Modifier,
    generations: Generations,
    onSelectedGeneration: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val region = stringResource(id = generations.nameRegion)
        CustomSingleText(
            modifier.fillMaxWidth(),
            stringResource(id = R.string.name_region, region),
            Color.White,
            8.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = generations.image),
            contentDescription = stringResource(id = R.string.cd_generation),
            modifier = modifier
                .fillMaxWidth()
                .scale(1.5f)
                .clickable {
                    onSelectedGeneration(generations.id)
                }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
