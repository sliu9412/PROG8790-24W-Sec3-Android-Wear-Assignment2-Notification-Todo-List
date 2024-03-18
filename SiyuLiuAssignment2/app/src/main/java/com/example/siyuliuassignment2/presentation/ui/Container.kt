package com.example.siyuliuassignment2.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.TimeTextDefaults
import androidx.wear.compose.material.Vignette
import androidx.wear.compose.material.VignettePosition

@Composable
fun Container(
    showVignette: Boolean = true,
    showTimeText: Boolean = true,
    content: @Composable () -> Unit
) {
    Scaffold(
        vignette = { if (showVignette) Vignette(vignettePosition = VignettePosition.TopAndBottom) },
        timeText = {
            if (showTimeText) TimeText(timeSource = TimeTextDefaults.timeSource("HH:mm"))
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}