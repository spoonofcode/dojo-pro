package com.spoonofcode.dojopro.feature.login.register

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
import com.spoonofcode.dojopro.resources.first_name
import com.spoonofcode.dojopro.resources.last_name
import com.spoonofcode.dojopro.resources.password
import com.spoonofcode.dojopro.resources.sign_up
import com.spoonofcode.dojopro.core.base.ui.Dimens
import com.spoonofcode.dojopro.core.base.ui.compose.Buttons
import com.spoonofcode.dojopro.core.base.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.base.ui.compose.Spacers
import com.spoonofcode.dojopro.core.base.ui.compose.TextFields
import com.spoonofcode.dojopro.core.base.ui.ext.koinViewModel
import com.spoonofcode.dojopro.core.navigation.NavigationHandler
import org.jetbrains.compose.resources.stringResource

class RegisterScreen : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<RegisterViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        ContentView(
            viewState = viewState,
            changeEmail = { viewModel.changeEmail(it) },
            changePassword = { viewModel.changePassword(it) },
            changeFirstName = { viewModel.changeFirstName(it) },
            changeLastName = { viewModel.changeLastName(it) },
            signUp = { viewModel.signUp() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: RegisterViewState,
        changeEmail: (String) -> Unit,
        changePassword: (String) -> Unit,
        changeFirstName: (String) -> Unit,
        changeLastName: (String) -> Unit,
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

                TextFields.Outlined(
                    value = viewState.firstName,
                    onValueChange = { changeFirstName(it) },
                    label = stringResource(resource = Res.string.first_name),
                )

                Spacers.VerticalBetweenFields()

                TextFields.Outlined(
                    value = viewState.lastName,
                    onValueChange = { changeLastName(it) },
                    label = stringResource(resource = Res.string.last_name),
                )

                Spacers.VerticalBetweenFields()

                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.sign_up),
                    onClick = { signUp() }
                )
            }
        }
    }
}