package tabs.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.email
import com.spoonofcode.dojopro.resources.forget_password
import com.spoonofcode.dojopro.resources.password
import com.spoonofcode.dojopro.resources.sign_in
import com.spoonofcode.dojopro.resources.sign_up
import core.ui.Dimens
import core.ui.compose.Buttons
import core.ui.compose.Buttons.GoogleSignInButton
import core.ui.compose.LoadingView
import core.ui.compose.Spacers
import core.ui.compose.TextFields
import core.ui.ext.koinViewModel
import navigation.NavigationHandler
import org.jetbrains.compose.resources.stringResource

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
            changeEmail = { viewModel.changeEmail(it) },
            changePassword = { viewModel.changePassword(it) },
            forgotPassword = { viewModel.forgotPassword() },
            signIn = { viewModel.signIn() },
            signInWithGoogle = { viewModel.signInWithGoogle(it) },
            signUp = { viewModel.signUp() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: LoginViewState,
        changeEmail: (String) -> Unit,
        changePassword: (String) -> Unit,
        forgotPassword: () -> Unit,
        signIn: () -> Unit,
        signInWithGoogle: (String) -> Unit,
        signUp: () -> Unit,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(all = Dimens.screenPadding),
            verticalArrangement = Arrangement.Center,
        ) {
            if (viewState.isViewLoading) {
                LoadingView()
            } else {
                TextFields.Outlined(
                    value = viewState.email,
                    onValueChange = { changeEmail(it) },
                    label = stringResource(resource = Res.string.email),
                )

                Spacers.VerticalBetweenFields()

                TextFields.OutlinedPassword(
                    value = viewState.password,
                    onValueChange = { changePassword(it) },
                    label = stringResource(resource = Res.string.password),
                )

                Spacers.VerticalBetweenFields()

                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.sign_in),
                    onClick = { signIn() }
                )

                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.forget_password),
                    onClick = { forgotPassword() }
                )

                Spacers.VerticalBetweenFields()

                GoogleSignInButton(onGoogleSignInResult = { googleUser ->
                    // send Google id token to your server
                    val googleIdToken = requireNotNull(googleUser?.token)
                    signInWithGoogle(googleIdToken)
                })

                Spacers.VerticalBetweenFields()

                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.sign_up),
                    onClick = { signUp() }
                )
            }
        }
    }
}