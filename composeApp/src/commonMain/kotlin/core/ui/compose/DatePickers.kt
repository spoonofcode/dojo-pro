package core.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.accept
import com.spoonofcode.dojopro.resources.cancel
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource

object DatePickers {

    @Composable
    fun CustomDatePicker(
        label: String,
        onValueChange: (String) -> Unit,
    ) {
        val date =
            remember {
                mutableStateOf(
                    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                )
            }
        val isOpen = remember { mutableStateOf(false) }

        TextFields.Outlined(
            readOnly = true,
            value = date.value.format(LocalDate.Formats.ISO),
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
            CustomDatePickerDialog(
                onAccept = {
                    isOpen.value = false // close dialog

                    if (it != null) { // Set the date
                        date.value = Instant
                            .fromEpochMilliseconds(it)
                            .toLocalDateTime(TimeZone.UTC).date
                    }
                },
                onCancel = {
                    isOpen.value = false //close dialog
                }
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomDatePickerDialog(
        onAccept: (Long?) -> Unit,
        onCancel: () -> Unit
    ) {
        val state = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = { onAccept(state.selectedDateMillis) }) {
                    Text(stringResource(resource = Res.string.accept))
                }
            },
            dismissButton = {
                Button(onClick = onCancel) {
                    Text(stringResource(resource = Res.string.cancel))
                }
            }
        ) {
            DatePicker(state = state)
        }
    }

}