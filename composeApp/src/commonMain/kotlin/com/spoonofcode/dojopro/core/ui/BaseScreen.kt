package com.spoonofcode.dojopro.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.dojopro.core.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.ui.ext.viewEnable
import com.spoonofcode.dojopro.core.ui.navigation.NavigationHandler
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

abstract class BaseScreen<VM : BaseViewModel<VS>, VS : BaseViewState>(
    open val screenTopAppBarTitle: StringResource? = null
) : Screen {

    @Composable
    protected abstract fun provideViewModel(): VM

    @Composable
    protected abstract fun provideContentView(
        viewModel: VM,
        viewState: VS
    ): @Composable ColumnScope.() -> Unit

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = provideViewModel()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        ContentView(
            content = provideContentView(viewModel, viewState),
            screenTopAppBarTitle = screenTopAppBarTitle,
            isLoadingView = viewState.isLoadingView,
            isEnableView = viewState.isEnableView,
        )
    }


    // TODO This functions should be private but now
    //  Android Studio will be support previews in commonMain
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentView(
        content: @Composable ColumnScope.() -> Unit,
        screenTopAppBarTitle: StringResource? = null,
        isLoadingView: Boolean = false,
        isEnableView: Boolean = false,
    ) {
        Scaffold(
            topBar = {
                if (screenTopAppBarTitle != null) {
                    TopAppBar(
                        title = { Text(stringResource(resource = screenTopAppBarTitle)) },
                    )
                }
            },
            modifier = Modifier.viewEnable(isEnableView && isLoadingView.not())
        ) {
            if (isLoadingView) {
                LoadingView()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(all = Dimens.screenPadding),
                    content = content
                )
            }
        }
    }
}