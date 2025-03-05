package com.spoonofcode.dojopro.core.ui

import androidx.compose.material3.SnackbarDuration

sealed class SnackbarEvent(
    val message: String,
    val duration: SnackbarDuration
) {
    data object Offline : SnackbarEvent(
        message = "No internet connection",
        duration = SnackbarDuration.Indefinite
    )

    data object Online : SnackbarEvent(
        message = "Internet connection restored",
        duration = SnackbarDuration.Short
    )

    class Error(
        message: String // Always required—no default
    ) : SnackbarEvent(
        message = message,
        duration = SnackbarDuration.Indefinite
    )
}