package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.RefreshRepository
import com.spoonofcode.dojopro.core.network.SessionManager

internal class RefreshUseCase(
    private val sessionManager: SessionManager,
    private val refreshRepository: RefreshRepository,
) {
    suspend fun refreshAccessToken(): Boolean {
        val jwtRefreshToken = sessionManager.getSessionRefreshToken() ?: return false
        val refresh = refreshRepository.refreshToken(jwtRefreshToken)
        sessionManager.saveSessionAccessToken(refresh.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(refresh.jwtRefreshToken)
        return true
    }
}