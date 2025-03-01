package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository
import com.spoonofcode.dojopro.core.model.LoginGoogleRequest
import com.spoonofcode.dojopro.core.network.SessionManager

class LoginGoogleUseCase(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        googleIdToken: String,
    ) {
        val loginGoogleResponse = loginGoogleRepository.create(
            request = LoginGoogleRequest(
                googleUserToken = googleIdToken,
            )
        )
        sessionManager.saveSessionUserId(userId = loginGoogleResponse.userId)
        sessionManager.saveSessionAccessToken(token = loginGoogleResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = loginGoogleResponse.jwtRefreshToken)
    }
}