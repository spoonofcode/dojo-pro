package core.ui.compose

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

object Texts {
    @Composable
    fun HS(
        text: String,
        modifier: Modifier = Modifier,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
    }

    @Composable
    fun HM(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }

    @Composable
    fun HL(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineLarge
        )
    }

    @Composable
    fun BS(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )
    }

    @Composable
    fun BM(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }

    @Composable
    fun BL(
        text: String,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}