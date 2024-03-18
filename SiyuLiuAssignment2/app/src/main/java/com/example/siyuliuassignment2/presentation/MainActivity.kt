/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.siyuliuassignment2.presentation

import DefaultScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.navDeepLink
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.siyuliuassignment2.presentation.Store.LocalStore
import com.example.siyuliuassignment2.presentation.Store.TaskItem
import com.example.siyuliuassignment2.presentation.screens.DueListScreen
import com.example.siyuliuassignment2.presentation.screens.ListScreen

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyNotificationManager(this)
        setContent {
            navController = Root(notifiedTasks)
        }
    }

    companion object {
        // Avoid the same notification to be pushed many times
        val notifiedTasks: MutableList<String> = mutableListOf<String>()
    }
}

@Composable
fun Root(notifiedTasks: MutableList<String>): NavHostController {
    val context = LocalContext.current
    val taskList = SnapshotStateList<TaskItem>()
    val url = "https://www.example.com"
    LocalStore(context).loadDataList().forEach { taskItem -> taskList.add(taskItem) }
    notificationTimer(context, taskList.toList(), notifiedTasks)

    val navController = rememberSwipeDismissableNavController()
    SwipeDismissableNavHost(
        navController = navController,
        startDestination = Routers.default.name,
        builder = {
            composable(
                Routers.default.name, deepLinks = listOf(navDeepLink {
                    uriPattern = "${url}/default"
                })
            ) {
                DefaultScreen(taskList = taskList, navController = navController)
            }
            composable(Routers.list.name, deepLinks = listOf(navDeepLink {
                uriPattern = "${url}/list"
            })) {
                ListScreen(taskList = taskList, navController = navController, tag = "All Tasks")
            }
            composable(Routers.due.name, deepLinks = listOf(navDeepLink {
                uriPattern = "${url}/due"
            })) {
                DueListScreen(navController)
            }
        })

    return navController
}


@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Root(mutableListOf<String>())
}