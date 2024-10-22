package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.pokequiz.R

@Composable
fun CustomMultiText(modifier: Modifier, bodyText: String, color: Color, size: TextUnit) {
    val customFont = FontFamily(Font(R.font.pokemon_gb, FontWeight.Normal))
    Text(
        text = bodyText,
        color = color,
        modifier = modifier
            .fillMaxWidth(),
        fontWeight = FontWeight.Bold,
        fontSize = size,
        textAlign = TextAlign.Center,
        fontFamily = customFont,
        lineHeight = 18.sp
    )
}