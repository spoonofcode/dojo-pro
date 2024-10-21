import androidx.compose.runtime.Composable
import cocoapods.GoogleSignIn.GIDSignIn
import kotlinx.cinterop.ExperimentalForeignApi
import model.GoogleAccount
import platform.UIKit.UIApplication
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider = GoogleAuthUiProvider()


    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun signOut() {
        GIDSignIn.sharedInstance.signOut()
    }
}