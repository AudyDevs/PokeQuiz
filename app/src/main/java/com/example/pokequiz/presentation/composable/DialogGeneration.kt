package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pokequiz.R
import com.example.pokequiz.core.Generations

@Composable
fun DialogGeneration(
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.DarkGray,
            modifier = Modifier
                .height(500.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    modifier = Modifier,
                    bodyText = stringResource(id = R.string.title_generation),
                    color = Color.White,
                    size = 10.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation1,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation2,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation3,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation4,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation5,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation6,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation7,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation8,
                        onSelectedGeneration = { id -> onConfirm(id) })
                    Spacer(modifier = Modifier.height(28.dp))
                    CustomGenerationButton(modifier = Modifier,
                        Generations.Generation9,
                        onSelectedGeneration = { id -> onConfirm(id) })
                }
            }
        }
    }
}