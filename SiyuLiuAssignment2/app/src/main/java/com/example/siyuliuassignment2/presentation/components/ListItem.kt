package com.example.siyuliuassignment2.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.CompactButton
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.siyuliuassignment2.presentation.Store.TaskItem
import java.time.format.DateTimeFormatter

@Composable
fun ListItem(
    index: Int, item: TaskItem, deleteListItem: (
        index: Int
    ) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 0.dp, 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = "Task ID", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = item.id.toString().slice(IntRange(0, 4)), fontSize = 12.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Task Name", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(text = item.taskName, fontSize = 12.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Task Due", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(
                text = item.due.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                fontSize = 12.sp
            )
        }
        CompactButton(onClick = {
            deleteListItem(index)
        }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}