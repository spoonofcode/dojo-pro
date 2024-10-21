import model.GoogleAccount

expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
}