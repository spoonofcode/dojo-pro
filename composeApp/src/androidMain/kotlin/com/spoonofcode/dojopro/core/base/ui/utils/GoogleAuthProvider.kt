package com.spoonofcode.dojopro.core.base.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager

actual class GoogleAuthProvider(
    private val credentialManager: CredentialManager
) {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider {
        val activityContext = LocalContext.current
        return com.spoonofcode.dojopro.core.base.ui.utils.GoogleAuthUiProvider(
            activityContext,
            credentialManager
        )
    }
}