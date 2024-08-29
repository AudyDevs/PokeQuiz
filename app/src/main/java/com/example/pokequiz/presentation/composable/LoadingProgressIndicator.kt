package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingOptionMenu() {
    CircularProgressIndicator(
        modifier = Modifier
            .size(50.dp),
        strokeWidth = 4.dp
    )
}