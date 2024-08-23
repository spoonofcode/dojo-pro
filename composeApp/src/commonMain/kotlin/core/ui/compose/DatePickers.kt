package core.ui.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    fun DatePicker(
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier.fillMaxWidth(),
        label: String? = null,
    ) {
        val date =
            remember {
                mutableStateOf(
                    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
                )
            }
        val isOpen = remember { mutableStateOf(false) }

        TextFields.Outlined(
            value = date.value.format(LocalDate.Formats.ISO),
            onValueChange = onValueChange,
            modifier = modifier,
            readOnly = true,
            label = label,
            trailingIcon = {
                IconButton(
                    onClick = { isOpen.value = true } // show de dialog
                ) {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Calendar")
                }
            }
        )

        if (isOpen.value) {
            DatePickerDialog(
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
    fun DatePickerWithTimer(
        label: String? = null,
    ) {
        label?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            Modifier.fillMaxWidth()
        ) {
            DatePicker(
                onValueChange = {},
                modifier = Modifier.wrapContentWidth().weight(1f),
            )
            Spacers.HorizontalBetweenFields()
            TimePickers.TimePicker(
                onValueChange = {},
                modifier = Modifier.wrapContentWidth().weight(1f),
                onConfirm = {},
                onDismiss = {},
            )
        }
        Spacers.VerticalBetweenFields()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun DatePickerDialog(
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