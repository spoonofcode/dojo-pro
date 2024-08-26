package core.ui.compose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import core.ext.formatedTime
import core.ui.utils.LocalDateTimeUtils
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

object TimePickers {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TimePicker(
        value: LocalDateTime = LocalDateTimeUtils.now(),
        onValueChange: (LocalDateTime) -> Unit,
        modifier: Modifier = Modifier,
        label: String? = null,
        onConfirm: (TimePickerState) -> Unit,
        onDismiss: () -> Unit,
    ) {
        val timePickerState = rememberTimePickerState(
            initialHour = value.hour,
            initialMinute = value.minute,
            is24Hour = true,
        )
        val isOpen = remember { mutableStateOf(false) }

        TextFields.Outlined(
            value = value.formatedTime(),
            modifier = modifier,
            readOnly = true,
            label = label,
            onValueChange = {},
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
                    onValueChange(
                        LocalDateTime(
                            value.date,
                            LocalTime(
                                hour = timePickerState.hour,
                                minute = timePickerState.minute,
                                second = 0,
                                nanosecond = 0
                            )
                        )
                    )

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
    private fun TimePickerDialog(
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