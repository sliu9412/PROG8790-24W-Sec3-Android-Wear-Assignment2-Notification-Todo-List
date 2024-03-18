package com.example.siyuliuassignment2.presentation.ui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon

@Composable
fun IconButton(
    icon: ImageVector,
    contentDescription: String,
    action: () -> Unit,
    size: Dp = 40.dp,
    enable: Boolean = true
) {
    Button(modifier = Modifier.size(size), enabled = enable, onClick = action) {
        Icon(imageVector = icon, contentDescription = contentDescription)
    }
}