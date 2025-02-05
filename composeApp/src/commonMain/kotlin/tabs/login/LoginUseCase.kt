package tabs.login

import SessionRepository
import model.LoginGoogleRequest
import model.LoginRequest
import repository.LoginGoogleRepository
import repository.LoginRepository

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

//        sessionRepository.clearSessionAccessToken()
//        sessionRepository.clearSessionRefreshToken()
        sessionRepository.saveSessionAccessToken(token = loginResponse.jwtAccessToken)
        sessionRepository.saveSessionRefreshToken(token = loginResponse.jwtRefreshToken)
    }

    suspend fun signInWithGoogle(googleIdToken: String) {
        val loginGoogleResponse = loginGoogleRepository.create(
            request = LoginGoogleRequest(
                googleUserToken = googleIdToken,
            )
        )
//        sessionRepository.clearSessionAccessToken()
//        sessionRepository.clearSessionRefreshToken()
        sessionRepository.saveSessionAccessToken(token = loginGoogleResponse.jwtAccessToken)
        sessionRepository.saveSessionRefreshToken(token = loginGoogleResponse.jwtRefreshToken)
    }
}