package core.ui.compose

import GoogleAuthProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.ic_google
import kotlinx.coroutines.launch
import model.GoogleAccount
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

object Buttons {

    @Composable
    fun PrimaryButton(
        text: String,
        onClick: () -> Unit,
    ): Unit = Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Text(text)
    }

//    @Composable
//    fun GoogleLoginButton(onClick: () -> Unit) {
//        Button(
//            onClick = onClick,
//            colors = ButtonDefaults.buttonColors(containerColor = Color(color = 0xFF2A3039)),
//            contentPadding = PaddingValues(16.dp),
//            modifier = Modifier.padding(horizontal = 16.dp),
//        ) {
//            Box(modifier = Modifier.fillMaxWidth()) {
//                Image(
//                    painter = painterResource(resource = Res.drawable.ic_google),
//                    contentDescription = null,
//                )
//
//                Text(
//                    text = "Continue with Google",
//                    color = Color(color = 0xFFF6F6F6),
//                    modifier = Modifier.align(Alignment.Center),
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.SemiBold,
//                )
//            }
//        }
//    }

    interface GoogleButtonClick {
        fun onSignInClicked()

        fun onSignOutClicked()
    }

    @Composable
    fun GoogleSignInButton(
        modifier: Modifier = Modifier,
        onGoogleSignInResult: (GoogleAccount?) -> Unit,
        content: @Composable GoogleButtonClick.() -> Unit,
    ) {
        val googleAuthProvider = koinInject<GoogleAuthProvider>()
        val googleAuthUiProvider = googleAuthProvider.getUiProvider()
        val coroutineScope = rememberCoroutineScope()
        val uiContainerScope =
            remember {
                object : GoogleButtonClick {
                    override fun onSignInClicked() {
                        coroutineScope.launch {
                            val googleUser = googleAuthUiProvider.signIn()
                            onGoogleSignInResult(googleUser)
                        }
                    }


                    override fun onSignOutClicked() {
                        coroutineScope.launch {
                            googleAuthProvider.signOut()
                        }
                    }
                }
            }
        Surface(
            modifier = modifier,
            content = { uiContainerScope.content() },
        )
    }

}