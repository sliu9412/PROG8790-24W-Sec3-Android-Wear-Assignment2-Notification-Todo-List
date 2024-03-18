package com.example.siyuliuassignment2.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.wear.compose.foundation.lazy.ScalingLazyColumn
import androidx.wear.compose.foundation.lazy.ScalingLazyListState
import androidx.wear.compose.foundation.lazy.itemsIndexed
import com.example.siyuliuassignment2.presentation.Routers
import com.example.siyuliuassignment2.presentation.Store.LocalStore
import com.example.siyuliuassignment2.presentation.Store.TaskItem
import com.example.siyuliuassignment2.presentation.components.ListItem
import com.example.siyuliuassignment2.presentation.theme.SiyuLiuAssignment2Theme
import com.example.siyuliuassignment2.presentation.ui.Container
import com.example.siyuliuassignment2.presentation.ui.IconButton
import com.example.siyuliuassignment2.presentation.ui.Label
import kotlinx.coroutines.launch

@Composable
fun ListScreen(
    taskList: SnapshotStateList<TaskItem>,
    navController: NavHostController,
    tag: String
) {
    val context = LocalContext.current
    val scalingLazyListState = ScalingLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val deleteListItem = { index: Int ->
        taskList.removeAt(index)
        LocalStore(context).saveDataList(taskList)
    }

    SiyuLiuAssignment2Theme {
        Container {
            Spacer(modifier = Modifier.height(15.dp))
            IconButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                action = { navController.navigate(Routers.default.name) })
            Label(text = tag)
            ScalingLazyColumn(
                modifier = Modifier.padding(10.dp, 0.dp),
                state = scalingLazyListState
            ) {
                itemsIndexed(taskList) { index, item ->
                    ListItem(index, item, deleteListItem = deleteListItem)
                }
            }

            coroutineScope.launch {
                scalingLazyListState.scrollToItem(0)
            }
        }
    }
}