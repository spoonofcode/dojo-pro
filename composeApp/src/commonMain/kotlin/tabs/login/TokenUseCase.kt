package tabs.login

import SessionRepository
import repository.LoginRepository

internal class TokenUseCase(
    private val sessionRepository: SessionRepository,
    private val loginRepository: LoginRepository,
) {
    suspend fun refreshAccessToken(): Boolean {
        val jwtRefreshToken = sessionRepository.getSessionRefreshToken() ?: return false
        val login = loginRepository.refreshToken(jwtRefreshToken)

        println("BARTEK jwtAccessToken =  ${login.jwtAccessToken}")
        println("BARTEK jwtRefreshToken = ${login.jwtRefreshToken}")

//        sessionRepository.clearSessionAccessToken()
//        sessionRepository.clearSessionRefreshToken()
        sessionRepository.saveSessionAccessToken(login.jwtAccessToken)
        sessionRepository.saveSessionRefreshToken(login.jwtRefreshToken)
        return true
    }
}