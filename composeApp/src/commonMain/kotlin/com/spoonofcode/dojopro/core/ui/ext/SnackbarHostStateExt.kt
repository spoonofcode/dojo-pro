package com.spoonofcode.dojopro.core.ui.ext

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.graphics.Color
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import com.spoonofcode.dojopro.core.ui.compose.MySnackbarVisuals

suspend fun SnackbarHostState.showSnackbar(
    snackbarEvent: SnackbarEvent,
) {
    showSnackbar(
        MySnackbarVisuals(
            message = snackbarEvent.message,
            actionLabel = "OK",
            containerColor = Color.Red,
            contentColor = Color.White,
            duration = snackbarEvent.duration
        )
    )
}