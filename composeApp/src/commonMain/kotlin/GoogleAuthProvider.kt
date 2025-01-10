import androidx.compose.runtime.Composable

expect class GoogleAuthProvider {
    @Composable
    fun getUiProvider(): GoogleAuthUiProvider
}