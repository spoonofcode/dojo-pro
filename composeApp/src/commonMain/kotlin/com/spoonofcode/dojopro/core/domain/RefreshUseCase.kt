package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.network.SessionManager
import com.spoonofcode.dojopro.core.data.repository.LoginRepository

internal class RefreshUseCase(
    private val sessionManager: SessionManager,
    private val loginRepository: LoginRepository,
) {
    suspend fun refreshAccessToken(): Boolean {
        val jwtRefreshToken = sessionManager.getSessionRefreshToken() ?: return false
        val login = loginRepository.refreshToken(jwtRefreshToken)
        sessionManager.saveSessionAccessToken(login.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(login.jwtRefreshToken)
        return true
    }
}