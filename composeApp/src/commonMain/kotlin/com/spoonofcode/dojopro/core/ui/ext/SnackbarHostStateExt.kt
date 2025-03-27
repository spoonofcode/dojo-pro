package com.spoonofcode.dojopro.core.ui.ext

import androidx.compose.material3.SnackbarHostState
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import com.spoonofcode.dojopro.core.ui.compose.CustomSnackbarVisuals

suspend fun SnackbarHostState.showSnackbar(
    snackbarEvent: SnackbarEvent,
) {
    showSnackbar(
        CustomSnackbarVisuals(
            message = snackbarEvent.message,
            actionLabel = snackbarEvent.actionLabel,
            containerColor = snackbarEvent.containerColor,
            contentColor = snackbarEvent.contentColor,
            duration = snackbarEvent.duration
        )
    )
}