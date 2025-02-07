package core.domain

import core.network.SessionRepository
import core.data.repository.LoginRepository

internal class TokenUseCase(
    private val sessionRepository: SessionRepository,
    private val loginRepository: LoginRepository,
) {
    suspend fun refreshAccessToken(): Boolean {
        val jwtRefreshToken = sessionRepository.getSessionRefreshToken() ?: return false
        val login = loginRepository.refreshToken(jwtRefreshToken)
        sessionRepository.saveSessionAccessToken(login.jwtAccessToken)
        sessionRepository.saveSessionRefreshToken(login.jwtRefreshToken)
        return true
    }
}