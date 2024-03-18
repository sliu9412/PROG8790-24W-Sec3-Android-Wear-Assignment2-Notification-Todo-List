package com.example.siyuliuassignment2.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.foundation.lazy.ScalingLazyListItemScope
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.dialog.Alert

@Composable
fun Modal(
    title: String,
    content: @Composable() (ScalingLazyListItemScope.() -> Unit),
    confirmAction: () -> Unit = {},
    cancelAction: () -> Unit = {}
): MutableState<Boolean> {
    val displayModal = remember {
        mutableStateOf(false)
    }
    if (displayModal.value) {
        Dialog(onDismissRequest = {}) {
            Alert(title = {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }, content = {
                item {
                    content()
                }
                item {
                    Row {
                        IconButton(
                            icon = Icons.Default.Close,
                            contentDescription = "Cancel",
                            action = {
                                cancelAction()
                                displayModal.value = false
                            }
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        IconButton(
                            icon = Icons.Default.Done,
                            contentDescription = "Confirm",
                            action = {
                                confirmAction()
                                displayModal.value = false
                            }
                        )
                    }
                }
            })
        }
    }
    return displayModal
}