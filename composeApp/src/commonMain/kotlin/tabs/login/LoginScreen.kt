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
import navigation.NavigationHandler

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<LoginViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        ContentView(
            viewState = viewState,
            sendGoogleToken = { viewModel.sendGoogleToken(it) },
            getTokenFromStore = { viewModel.getTokenFromStore() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: LoginViewState,
        sendGoogleToken: (String) -> Unit,
        getTokenFromStore: () -> Unit,
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

            Text("Received token from Server")
            Text(viewState.receivedToken)

            Button(onClick = { getTokenFromStore() }) {
                Text("Get Token from Store!")
            }
            Text("Received token from Store")
            Text(viewState.receivedTokenFromStore)
        }
    }
}