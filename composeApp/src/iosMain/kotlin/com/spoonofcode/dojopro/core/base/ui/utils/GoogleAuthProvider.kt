package com.spoonofcode.dojopro.core.base.ui.utils

import androidx.compose.runtime.Composable

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider = GoogleAuthUiProvider()
}