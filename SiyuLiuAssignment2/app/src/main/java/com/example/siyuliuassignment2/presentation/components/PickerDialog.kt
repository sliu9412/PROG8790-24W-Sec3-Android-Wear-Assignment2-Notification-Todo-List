package com.example.siyuliuassignment2.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Picker
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.rememberPickerState
import com.example.siyuliuassignment2.presentation.ui.Modal

data class PickerReturn(
    val displayDialog: MutableState<Boolean>,
    val currentItem: String
)

@Composable
fun PickerDialog(
    title: String, data: List<String>, confirmAction: () -> Unit = {},
    cancelAction: () -> Unit = {}
): PickerReturn {
    val state = rememberPickerState(
        initialNumberOfOptions = data.size,
        initiallySelectedOption = 0,
        repeatItems = false
    )

    val displayDialog = Modal(title = title, content = {
        Picker(
            modifier = Modifier.size(140.dp, 80.dp),
            state = state,
            contentDescription = "Picker",
        ) {
            Text(
                modifier = Modifier.padding(0.dp, 5.dp),
                text = data[it],
                fontSize = 25.sp
            )
        }
    }, confirmAction, cancelAction)
    return PickerReturn(displayDialog, data[state.selectedOption])
}