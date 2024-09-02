package com.example.pokequiz.presentation.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.pokequiz.presentation.composable.CustomMenuButton
import com.example.pokequiz.presentation.composable.CustomMultiText
import com.example.pokequiz.presentation.composable.CustomTextField

@Composable
fun DialogNameTrainer(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var nameTrainerSelected = ""
    Dialog(onDismissRequest = { onDismiss() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profesor_oak),
                contentDescription = "",
                Modifier
                    .align(Alignment.Center)
                    .offset(y = (-100).dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomMultiText(
                        modifier = Modifier,
                        bodyText = stringResource(id = R.string.home_name_trainer_body),
                        color = Color.Black,
                        size = 12.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomTextField(onValueChange = { nameTrainer ->
                        nameTrainerSelected = nameTrainer
                    })
                    Spacer(modifier = Modifier.height(6.dp))
                    CustomMenuButton(
                        modifier = Modifier.clickable {
                            if (nameTrainerSelected.isNotEmpty()) {
                                onConfirm(nameTrainerSelected)
                            }
                        },
                        40.dp,
                        44.dp,
                        painter = painterResource(id = R.drawable.ic_pokeball),
                        titleButton = stringResource(
                            id = R.string.accept
                        )
                    )
                }
            }
        }
    }
}