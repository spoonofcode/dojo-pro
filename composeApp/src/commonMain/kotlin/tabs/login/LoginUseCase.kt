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

        sessionRepository.saveSessionToken(token = loginResponse.jwtToken)
    }

    suspend fun signInWithGoogle(googleIdToken: String) {
        val loginGoogleResponse = loginGoogleRepository.create(
            request = LoginGoogleRequest(
                googleUserToken = googleIdToken,
            )
        )

        sessionRepository.saveSessionToken(token = loginGoogleResponse.jwtToken)
    }
}