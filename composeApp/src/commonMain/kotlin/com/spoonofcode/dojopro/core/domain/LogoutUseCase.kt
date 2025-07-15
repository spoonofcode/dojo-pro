package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.network.SessionManager

class LogoutUseCase(
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke() {
        sessionManager.clearSessionUserId()
        sessionManager.clearSessionAccessToken()
        sessionManager.clearSessionRefreshToken()
    }
}