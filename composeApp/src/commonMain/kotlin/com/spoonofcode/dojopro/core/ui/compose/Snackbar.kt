package com.spoonofcode.dojopro.core.ui.compose

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class MySnackbarVisuals(
    override val message: String,
    override val actionLabel: String? = null,
    override val withDismissAction: Boolean = false,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    // Custom fields
    val containerColor: Color = Color.Gray,
    val contentColor: Color = Color.White
) : SnackbarVisuals

@Composable
fun Snackbar(
    snackbarData: SnackbarData
) {
    val visuals = snackbarData.visuals
    if (visuals is MySnackbarVisuals) {
        // Our custom visuals
        Snackbar(
            containerColor = visuals.containerColor,
            contentColor = visuals.contentColor,
            action = {
                visuals.actionLabel?.let { actionLabel ->
                    TextButton(onClick = { snackbarData.performAction() }) {
                        Text(text = actionLabel)
                    }
                }
            }
        ) {
            Text(text = visuals.message)
        }
    } else {
        // Fallback: standard Snackbar
        Snackbar {
            Text(text = visuals.message)
        }
    }
}