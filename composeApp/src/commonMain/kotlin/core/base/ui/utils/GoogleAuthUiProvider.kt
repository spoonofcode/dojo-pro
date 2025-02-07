package core.base.ui.utils

import core.model.GoogleAccount

expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
    suspend fun signOut()
}