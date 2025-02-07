package com.spoonofcode.dojopro.core.base.ui.utils

import com.spoonofcode.dojopro.core.model.GoogleAccount

expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
    suspend fun signOut()
}