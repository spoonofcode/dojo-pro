import com.russhwolf.settings.Settings

class SessionRepository(private val settings: Settings) {

    companion object {
        private const val SESSION_TOKEN_KEY = "session_token"
    }

    suspend fun saveSessionToken(token: String) {
        settings.putString(SESSION_TOKEN_KEY, token)
    }

    suspend fun getSessionToken(): String? {
        return settings.getStringOrNull(SESSION_TOKEN_KEY)
    }

    suspend fun clearSessionToken() {
        settings.remove(SESSION_TOKEN_KEY)
    }
}