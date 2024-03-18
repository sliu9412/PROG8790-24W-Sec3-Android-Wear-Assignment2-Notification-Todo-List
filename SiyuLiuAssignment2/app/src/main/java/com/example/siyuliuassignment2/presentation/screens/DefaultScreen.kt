import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Text
import com.example.siyuliuassignment2.presentation.Routers
import com.example.siyuliuassignment2.presentation.Store.LocalStore
import com.example.siyuliuassignment2.presentation.Store.TaskItem
import com.example.siyuliuassignment2.presentation.components.PickerDialog
import com.example.siyuliuassignment2.presentation.components.PickerReturn
import com.example.siyuliuassignment2.presentation.theme.SiyuLiuAssignment2Theme
import com.example.siyuliuassignment2.presentation.ui.Container
import com.example.siyuliuassignment2.presentation.ui.IconButton
import com.example.siyuliuassignment2.presentation.ui.InputField
import com.example.siyuliuassignment2.presentation.ui.Label
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Composable
fun DefaultScreen(taskList: SnapshotStateList<TaskItem>, navController: NavHostController) {

    val context = LocalContext.current
    val taskName = remember {
        mutableStateOf("")
    }
    val taskDue = remember {
        mutableStateOf(LocalDateTime.now())
    }

    val isDueSet = remember {
        mutableStateOf(false)
    }

    val daysData = (0..7).map { day -> day.toString() }
    val hoursData = (0..24).map { hour -> hour.toString() }
    val minutesData = (0..60).map { minute -> minute.toString() }

    lateinit var daysPicker: PickerReturn
    lateinit var hourPicker: PickerReturn
    lateinit var minutePicker: PickerReturn

    val displayedDueString = remember {
        mutableStateOf("")
    }

    val setDisplayedDueString = {
        val currentDateTime = LocalDateTime.now()
        taskDue.value = currentDateTime.plusDays(daysPicker.currentItem.toLong())
            .plusHours(hourPicker.currentItem.toLong())
            .plusMinutes(minutePicker.currentItem.toLong())
        val formattedDue = taskDue.value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        displayedDueString.value = formattedDue
        isDueSet.value = true
    }

    SiyuLiuAssignment2Theme {
        daysPicker = PickerDialog(
            title = "Days later",
            data = daysData,
            confirmAction = {
                hourPicker.displayDialog.value = true
            })
        hourPicker =
            PickerDialog(title = "Hours later", data = hoursData, confirmAction = {
                minutePicker.displayDialog.value = true
            })
        minutePicker = PickerDialog(
            title = "Minutes later",
            data = minutesData,
            confirmAction = setDisplayedDueString
        )
        Container {
            Label(text = "Task Name")
            taskName.value = InputField(
                modifier = Modifier
                    .width(120.dp)
                    .height(30.dp)
            )
            Label(text = "Due Date & Time")
            Chip(
                modifier = Modifier
                    .width(120.dp)
                    .height(30.dp),
                onClick = { daysPicker.displayDialog.value = true },
                colors = ChipDefaults.chipColors(backgroundColor = Color.DarkGray),
                border = ChipDefaults.chipBorder()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                        .align(Alignment.CenterVertically),
                    text = displayedDueString.value,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                IconButton(
                    icon = Icons.AutoMirrored.Filled.List,
                    contentDescription = "To List Page",
                    action = {
                        navController.navigate(Routers.list.name)
                    })
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(
                    icon = Icons.Default.Add,
                    contentDescription = "Add Task",
                    enable = isDueSet.value && taskName.value !== "",
                    action = {
                        taskList.add(
                            0,
                            TaskItem(
                                UUID.randomUUID().toString(),
                                taskName.value,
                                taskDue.value
                            )
                        )
                        LocalStore(context).saveDataList(taskList)
                        navController.navigate(Routers.list.name)
                    })
            }
        }
    }
}