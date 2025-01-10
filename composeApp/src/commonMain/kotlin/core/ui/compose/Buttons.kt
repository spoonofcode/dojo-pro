package core.ui.compose

import GoogleAuthProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.ic_google
import kotlinx.coroutines.launch
import model.GoogleAccount
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

object Buttons {

    @Composable
    fun PrimaryButton(
        text: String,
        leftIcon: DrawableResource? = null,
        onClick: () -> Unit,
    ): Unit = Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {

        if (leftIcon != null) {
            Image(
                painter = painterResource(resource = Res.drawable.ic_google),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Text(text)
    }

    @Composable
    fun GoogleSignInButton(
        onGoogleSignInResult: (GoogleAccount?) -> Unit,
    ) {
        val googleAuthProvider = koinInject<GoogleAuthProvider>()
        val googleAuthUiProvider = googleAuthProvider.getUiProvider()
        val coroutineScope = rememberCoroutineScope()
        PrimaryButton(
            text = "Sign in with Google",
            leftIcon = Res.drawable.ic_google,
            onClick = {
                coroutineScope.launch {
                    val googleUser = googleAuthUiProvider.signIn()
                    onGoogleSignInResult(googleUser)
                }
            }
        )
    }

    @Composable
    fun GoogleSignOutButton() {
        val coroutineScope = rememberCoroutineScope()
        val googleAuthProvider = koinInject<GoogleAuthProvider>()
        val googleAuthUiProvider = googleAuthProvider.getUiProvider()

        PrimaryButton(
            text = "Sign out with Google",
            leftIcon = Res.drawable.ic_google,
            onClick = {
                coroutineScope.launch {
                    googleAuthUiProvider.signOut()
                }
            }
        )
    }

}