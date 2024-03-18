package com.example.siyuliuassignment2.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.siyuliuassignment2.presentation.Store.LocalStore
import com.example.siyuliuassignment2.presentation.Store.TaskItem
import java.time.LocalDateTime

@Composable
fun DueListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val taskList = LocalStore(context).loadDataList()
    val currentDateTime = LocalDateTime.now()
    val stateList = mutableStateListOf<TaskItem>()
    taskList.filter { taskItem ->
        currentDateTime.plusHours(1).isAfter(taskItem.due) && !taskItem.due.isBefore(
            currentDateTime
        )
    }
        .forEach { taskItem -> stateList.add(taskItem) }
    ListScreen(taskList = stateList, navController = navController, tag = "Upcoming Tasks")
}