package com.example.siyuliuassignment2.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text

@Composable
fun InputField(modifier: Modifier): String {
    val text = remember {
        mutableStateOf("")
    }
    Chip(
        modifier = modifier,
        onClick = {},
        colors = ChipDefaults.primaryChipColors(
            backgroundColor = Color.DarkGray
        ),
        border = ChipDefaults.chipBorder()
    ) {
        BasicTextField(
            modifier = Modifier.fillMaxSize(),
            value = text.value,
            onValueChange = { newValue -> text.value = newValue },
            singleLine = true
        ) {
            Text(
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                text = text.value,
                fontSize = 14.sp
            )
        }
    }
    return text.value
}