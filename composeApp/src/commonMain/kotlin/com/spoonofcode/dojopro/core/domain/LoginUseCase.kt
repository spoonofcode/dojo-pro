package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository
import com.spoonofcode.dojopro.core.data.repository.LoginRepository
import com.spoonofcode.dojopro.core.model.LoginGoogleRequest
import com.spoonofcode.dojopro.core.model.LoginRequest
import com.spoonofcode.dojopro.core.network.SessionManager

internal class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) {
    suspend fun signIn(email: String, password: String) {
        val loginResponse = loginRepository.create(
            LoginRequest(
                email = email,
                password = password,
            )
        )
        sessionManager.saveSessionUserId(userId = loginResponse.userId)
        sessionManager.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
    }

    suspend fun signInWithGoogle(googleIdToken: String) {
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