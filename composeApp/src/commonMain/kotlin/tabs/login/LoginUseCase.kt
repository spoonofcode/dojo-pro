package tabs.login

import SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
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
        runCatching {
            withContext(Dispatchers.IO) {
                loginRepository.create(
                    LoginRequest(
                        email = email,
                        password = password,
                    )
                )
            }
        }.onSuccess { login ->
            runCatching {
                withContext(Dispatchers.IO) {
                    sessionRepository.saveSessionToken(token = login.jwtToken)
                }
            }
        }.onFailure {
            throw it
        }
    }

    suspend fun signInWithGoogle(googleIdToken: String) {
        runCatching {
            withContext(Dispatchers.IO) {
                loginGoogleRepository.create(
                    request = LoginGoogleRequest(
                        googleUserToken = googleIdToken,
                    )
                )
            }
        }.onSuccess { login ->
            runCatching {
                withContext(Dispatchers.IO) {
                    sessionRepository.saveSessionToken(token = login.jwtToken)
                }
            }
        }.onFailure {
            throw it
        }
    }

}