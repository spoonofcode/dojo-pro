import com.russhwolf.settings.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class SessionRepository(private val settings: Settings) {

    companion object {
        private const val SESSION_TOKEN_KEY = "session_token"
    }

    suspend fun saveSessionToken(token: String) {
        withContext(Dispatchers.IO) {
            settings.putString(SESSION_TOKEN_KEY, token)
        }
    }

    suspend fun getSessionToken(): String? {
        return withContext(Dispatchers.IO) {
            settings.getStringOrNull(SESSION_TOKEN_KEY)
        }
    }

    suspend fun clearSessionToken() {
        return withContext(Dispatchers.IO) {
            settings.remove(SESSION_TOKEN_KEY)
        }
    }
}