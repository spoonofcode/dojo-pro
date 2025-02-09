package com.spoonofcode.dojopro.feature.login.forgotPassword

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
import com.spoonofcode.dojopro.resources.sign_up
import com.spoonofcode.dojopro.core.ui.Dimens
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.TextFields
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.core.ui.navigation.NavigationHandler
import org.jetbrains.compose.resources.stringResource

class ForgotPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<ForgotPasswordViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        ContentView(
            viewState = viewState,
            changeEmail = { viewModel.changeEmail(it) },
            resetPassword = { viewModel.resetPassword() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: ForgotPasswordViewState,
        changeEmail: (String) -> Unit,
        resetPassword: () -> Unit,
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

                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.sign_up),
                    onClick = { resetPassword() }
                )
            }
        }
    }
}