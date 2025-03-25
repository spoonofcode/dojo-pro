package com.spoonofcode.dojopro.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.spoonofcode.dojopro.core.ui.ext.addIf
import com.spoonofcode.dojopro.core.ui.ext.viewEnable
import com.spoonofcode.dojopro.core.ui.navigation.NavigationHandler
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

abstract class BaseScreen<VM : BaseViewModel<VS>, VS : BaseViewState>(
    open val screenTopAppBarTitle: StringResource? = null,
    open val backNavigationEnable: Boolean = true,
    open val verticalScrollEnable: Boolean = true,
    open val contentPadding: PaddingValues = PaddingValues(Dimens.screenPadding)
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
        val navigator: Navigator =
            LocalNavigator.currentOrThrow.parent ?: LocalNavigator.currentOrThrow

        val viewModel = provideViewModel()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        ContentView(
            content = provideContentView(viewModel, viewState),
            screenTopAppBarTitle = screenTopAppBarTitle,
            backNavigationEnable = backNavigationEnable,
            isLoadingView = viewState.isLoadingView,
            isEnableView = viewState.isEnableView,
            onBackClick = { navigator.pop() }
        )
    }


    // TODO This functions should be private but now
    //  Android Studio will be support previews in commonMain
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentView(
        content: @Composable ColumnScope.() -> Unit,
        screenTopAppBarTitle: StringResource? = null,
        backNavigationEnable: Boolean = true,
        onBackClick: () -> Unit = {},
        isLoadingView: Boolean = false,
        isEnableView: Boolean = false,
    ) {
        Scaffold(
            topBar = {
                if (screenTopAppBarTitle != null) {
                    TopAppBar(
                        navigationIcon = if (backNavigationEnable) {
                            {
                                IconButton(onClick = onBackClick) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back"
                                    )
                                }
                            }
                        } else {
                            {}
                        },
                        title = { Text(stringResource(resource = screenTopAppBarTitle)) },
                    )
                }
            },
            modifier = Modifier.viewEnable(isEnableView && isLoadingView.not()).fillMaxSize()
        ) { innerPadding ->
            if (isLoadingView) {
                LoadingView()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .addIf(verticalScrollEnable) {
                            verticalScroll(rememberScrollState())
                        }
                        .padding(innerPadding)
                        .padding(contentPadding),
                    content = content
                )
            }
        }
    }
}