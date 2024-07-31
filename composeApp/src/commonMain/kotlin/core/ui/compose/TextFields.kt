package core.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object TextFields {
    @Composable
    fun Outlined(
        value: String,
        label: String,
        onValueChange: (String) -> Unit,
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacers.BetweenFields()
    }
}