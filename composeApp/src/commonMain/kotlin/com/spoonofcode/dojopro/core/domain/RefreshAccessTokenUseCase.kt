package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.RefreshRepository
import com.spoonofcode.dojopro.core.network.SessionManager

class RefreshAccessTokenUseCase(
    private val refreshRepository: RefreshRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke() : Boolean {
        val jwtRefreshToken = sessionManager.getSessionRefreshToken() ?: return false
        val refresh = refreshRepository.refreshToken(jwtRefreshToken)
        sessionManager.saveSessionAccessToken(refresh.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(refresh.jwtRefreshToken)
        return true
    }
}