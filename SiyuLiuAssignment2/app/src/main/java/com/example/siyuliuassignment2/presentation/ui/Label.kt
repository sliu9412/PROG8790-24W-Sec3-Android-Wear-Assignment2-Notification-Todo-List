package com.example.siyuliuassignment2.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Text

@Composable
fun Label(modifier: Modifier = Modifier.padding(0.dp, 6.dp), text: String) {
    Text(modifier = modifier, text = text, fontSize = 12.sp)
}