package com.example.siyuliuassignment2.presentation

import android.content.Context
import com.example.siyuliuassignment2.presentation.Store.TaskItem
import java.time.LocalDateTime
import java.util.Timer
import java.util.TimerTask

fun notificationTimer(
    context: Context,
    taskList: List<TaskItem>,
    notifiedTasks: MutableList<String>
) {
    return Timer().scheduleAtFixedRate(object : TimerTask() {
        override fun run() {
            val currentDateTime = LocalDateTime.now()
            val expiringTasks = taskList
                .filter { taskItem ->
                    currentDateTime.plusHours(1).isAfter(taskItem.due) && !taskItem.due.isBefore(
                        currentDateTime
                    )
                }
            expiringTasks.forEach { taskItem ->
                if (taskItem.id !in notifiedTasks) {
                    notifiedTasks.add(taskItem.id)
                    MyNotificationManager.pushExpirationNotification(
                        context,
                        "${taskItem.taskName} will due",
                        "${taskItem.taskName} will due in one hour"
                    )
                }
            }
        }
    }, 0, 30000)
}