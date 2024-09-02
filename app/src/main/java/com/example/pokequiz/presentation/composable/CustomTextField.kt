package com.example.pokequiz.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokequiz.R

@Composable
fun CustomTextField(onValueChange: (String) -> Unit) {
    var nameTrainer by remember { mutableStateOf("") }
    val customFont = FontFamily(Font(R.font.pokemon_gb, FontWeight.Normal))

    TextField(
        value = nameTrainer,
        onValueChange = {
            nameTrainer = it
            onValueChange(nameTrainer)
        },
        placeholder = {
            CustomSingleText(
                modifier = Modifier.fillMaxWidth(),
                bodyText = stringResource(id = R.string.nameTrainer_editText),
                color = Color.Black,
                size = 11.sp
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.LightGray,
            unfocusedContainerColor = Color.LightGray,
            disabledContainerColor = Color.LightGray,
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}