package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.data.repository.RegisterRepository
import com.spoonofcode.dojopro.core.model.RegisterRequest
import com.spoonofcode.dojopro.core.network.SessionManager

internal class RegisterUseCase(
    private val registerRepository: RegisterRepository,
    private val sessionManager: SessionManager,
) {
    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        val registerResponse = registerRepository.create(
            RegisterRequest(
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
            )
        )
        sessionManager.saveSessionUserId(userId = registerResponse.userId)
        sessionManager.saveSessionAccessToken(token = registerResponse.jwtAccessToken)
        sessionManager.saveSessionRefreshToken(token = registerResponse.jwtRefreshToken)
    }
}