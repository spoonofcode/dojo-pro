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
import core.ui.Dimens
import core.ui.compose.Buttons
import core.ui.compose.LoadingView
import core.ui.ext.koinViewModel
import tabs.mainhost.MainHostScreen

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
            }
        )
    }

    @Composable
    internal fun ContentView(
        viewState: LoginViewState,
        goToHome: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimens.screenPadding),
            verticalArrangement = Arrangement.Center
        ) {
            if (viewState.isViewLoading) {
                LoadingView()
            } else {
                Buttons.PrimaryButton(
                    text = "Login",
                    onClick = goToHome
                )
            }
        }
    }

}