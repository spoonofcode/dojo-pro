package core.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object Buttons {

    @Composable
    fun PrimaryButton(
        text: String,
        onClick: () -> Unit,
    ): Unit = Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Text(text)
    }


}