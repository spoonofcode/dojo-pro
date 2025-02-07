package com.spoonofcode.dojopro.core.ui.utils

import androidx.compose.runtime.Composable

expect class GoogleAuthProvider {
    @Composable
    fun getUiProvider(): GoogleAuthUiProvider
}