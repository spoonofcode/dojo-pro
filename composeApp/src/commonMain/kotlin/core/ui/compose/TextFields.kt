package core.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

object TextFields {
    @Composable
    fun Outlined(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier.fillMaxWidth(),
        enabled: Boolean = true,
        readOnly: Boolean = false,
        label: String? = null,
        innerLabel: String? = null,
        trailingIcon: @Composable (() -> Unit)? = null,
        isError: Boolean = false,
    ) {
        label?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Bold
            )
        }
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            enabled = enabled,
            readOnly = readOnly,
            label = { innerLabel?.let { Text(text = it) } },
            trailingIcon = trailingIcon,
            isError = isError,
        )
        Spacers.VerticalBetweenFields()
    }
}