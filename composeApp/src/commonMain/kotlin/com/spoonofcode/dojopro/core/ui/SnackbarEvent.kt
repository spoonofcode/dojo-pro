package com.spoonofcode.dojopro.core.ui

import androidx.compose.material3.SnackbarDuration
import androidx.compose.ui.graphics.Color

sealed class SnackbarEvent(
    val message: String,
    val actionLabel: String? = null,
    val duration: SnackbarDuration,
    val containerColor: Color,
    val contentColor: Color,
) {
    data object Offline : SnackbarEvent(
        message = "No internet connection",
        duration = SnackbarDuration.Indefinite,
        containerColor = Color.Red,
        contentColor = Color.White,
    )

    data object Online : SnackbarEvent(
        message = "Internet connection restored",
        duration = SnackbarDuration.Short,
        containerColor = Color.Green,
        contentColor = Color.White,

        )

    class Error(
        message: String // Always required—no default
    ) : SnackbarEvent(
        message = message,
        actionLabel = "OK",
        duration = SnackbarDuration.Indefinite,
        containerColor = Color.Red,
        contentColor = Color.White,
    )
}