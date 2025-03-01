package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.LoginRepository
import com.spoonofcode.dojopro.core.model.LoginRequest
import com.spoonofcode.dojopro.core.network.SessionManager

class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val sessionManager: SessionManager,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ) {
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
}