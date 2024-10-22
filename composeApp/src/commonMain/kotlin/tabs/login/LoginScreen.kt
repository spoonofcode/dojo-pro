package tabs.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import core.ui.compose.Buttons.GoogleSignInButton
import core.ui.ext.koinViewModel
import tabs.mainhost.MainHostScreen

//import com.google.android.libraries.identity.googleid.GetGoogleIdOption
//import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
//import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<LoginViewModel>()
        val viewState by viewModel.viewState.collectAsState()

//        LaunchedEffect(Unit) {
//            viewModel.initView()
//        }

        ContentView(
            viewState = viewState,
            goToHome = {
                navigator.replaceAll(MainHostScreen())
            },
            sendGoogleToken = { viewModel.sendGoogleToken(it) },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: LoginViewState,
        goToHome: () -> Unit,
        sendGoogleToken: (String) -> Unit,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GoogleSignInButton(onGoogleSignInResult = { googleUser ->
                // send Google id token to your server
                val idToken = requireNotNull(googleUser?.token)
                println("BARTEK googleUser.idToken = $idToken")
                sendGoogleToken(idToken)
            }) {
                Button(onClick = { this.onSignInClicked() }) { Text("Google Sign-In(Custom Design)") }
            }

            Text(
                viewState.receivedToken
            )
        }


//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(Dimens.screenPadding),
//            verticalArrangement = Arrangement.Center
//        ) {
//            if (viewState.isViewLoading) {
//                LoadingView()
//            } else {
////                Buttons.PrimaryButton(
////                    text = "Login",
////                    onClick = goToHome
////                )
//
////                Buttons.GoogleLoginButton(
////                    onClick = {
////                        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
////                            .setFilterByAuthorizedAccounts(false) // Query all google accounts on the device
////                            .setServerClientId(SERVER_CLIENT_ID)
////                            .build()
////
////                        val request = GetCredentialRequest.Builder()
////                            .addCredentialOption(googleIdOption)
////                            .build()
////
////                        val credentialManager = CredentialManager.create(context)
////
////                        coroutineScope.launch {
////                            try {
////                                val result = credentialManager.getCredential(context, request)
////                                handleSignIn(result)
////                            } catch (e: GetCredentialException) {
////                                Log.e("MainActivity", "GetCredentialException", e)
////                            }
////                        }
////                    }
////                )
//            }
//        }
    }
}