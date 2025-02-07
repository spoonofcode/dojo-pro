package com.spoonofcode.dojopro.core.base.ui.utils

import androidx.compose.runtime.Composable

expect class GoogleAuthProvider {
    @Composable
    fun getUiProvider(): GoogleAuthUiProvider
}