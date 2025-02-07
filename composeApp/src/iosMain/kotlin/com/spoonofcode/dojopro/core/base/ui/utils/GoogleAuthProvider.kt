package com.spoonofcode.dojopro.core.base.ui.utils

import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.utils.GoogleAuthUiProvider

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider =
        com.spoonofcode.dojopro.core.ui.utils.GoogleAuthUiProvider()
}