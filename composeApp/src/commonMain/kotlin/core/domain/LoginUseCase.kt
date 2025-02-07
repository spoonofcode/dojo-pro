package core.domain

import core.network.SessionRepository
import core.model.LoginGoogleRequest
import core.model.LoginRequest
import core.data.repository.LoginGoogleRepository
import core.data.repository.LoginRepository

internal class LoginUseCase(
    private val loginRepository: LoginRepository,
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionRepository: SessionRepository,
) {
    suspend fun signIn(email: String, password: String) {
        val loginResponse = loginRepository.create(
            LoginRequest(
                email = email,
                password = password,
            )
        )
        sessionRepository.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
        sessionRepository.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
    }

    suspend fun signInWithGoogle(googleIdToken: String) {
        val loginGoogleResponse = loginGoogleRepository.create(
            request = LoginGoogleRequest(
                googleUserToken = googleIdToken,
            )
        )
        sessionRepository.saveSessionAccessToken(token = loginGoogleResponse.jwtAccessToken)
        sessionRepository.saveSessionRefreshToken(token = loginGoogleResponse.jwtRefreshToken)
    }
}