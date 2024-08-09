package core.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime

object TimePickers {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTimePicker(
        label: String,
        onValueChange: (String) -> Unit,
        onConfirm: (TimePickerState) -> Unit,
        onDismiss: () -> Unit,
    ) {
        val timePickerState = rememberTimePickerState(
            initialHour = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).hour,
            initialMinute = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault()).minute,
            is24Hour = true,
        )
        val isOpen = remember { mutableStateOf(false) }

        TextFields.Outlined(
            readOnly = true,
            value = timePickerState.hour.toString() + ":" + timePickerState.minute.toString(),
            label = label,
            onValueChange = onValueChange,
            trailingIcon = {
                IconButton(
                    onClick = { isOpen.value = true } // show de dialog
                ) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar")
                }
            }
        )

        if (isOpen.value) {
            TimePickerDialog(
                onDismiss = {
                    isOpen.value = false // close dialog
                    onDismiss()
                },
                onConfirm = {
                    isOpen.value = false //close dialog
                    onConfirm(timePickerState)
                }
            ) {
                TimePicker(
                    state = timePickerState,
                )
            }
        }
    }

    @Composable
    fun TimePickerDialog(
        onDismiss: () -> Unit,
        onConfirm: () -> Unit,
        content: @Composable () -> Unit
    ) {
        AlertDialog(
            onDismissRequest = onDismiss,
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text("Dismiss")
                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text("OK")
                }
            },
            text = { content() }
        )
    }
}