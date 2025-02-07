package com.spoonofcode.dojopro.feature.login.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.spoonofcode.dojopro.core.base.ui.Dimens
import com.spoonofcode.dojopro.core.base.ui.compose.Buttons
import com.spoonofcode.dojopro.core.base.ui.compose.Buttons.GoogleSignInButton
import com.spoonofcode.dojopro.core.base.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.base.ui.compose.Spacers
import com.spoonofcode.dojopro.core.base.ui.compose.TextFields
import com.spoonofcode.dojopro.core.base.ui.ext.koinViewModel
import kotlinx.coroutines.launch
import com.spoonofcode.dojopro.core.navigation.NavigationHandler
import org.jetbrains.compose.resources.stringResource

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<LoginViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        LaunchedEffect(viewModel.snackbarEvent) {
            viewModel.snackbarEvent.collect { message ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = message,
                        actionLabel = "OK",
                        duration = SnackbarDuration.Indefinite
                    )
                }
            }
        }

        ContentView(
            snackbarHostState = snackbarHostState,
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
        snackbarHostState: SnackbarHostState,
        viewState: LoginViewState,
        changeEmail: (String) -> Unit,
        changePassword: (String) -> Unit,
        forgotPassword: () -> Unit,
        signIn: () -> Unit,
        signInWithGoogle: (String) -> Unit,
        signUp: () -> Unit,
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
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
                        val googleUserToken = requireNotNull(googleUser?.token)
                        signInWithGoogle(googleUserToken)
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
}