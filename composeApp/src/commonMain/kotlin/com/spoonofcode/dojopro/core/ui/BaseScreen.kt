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
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.dojopro.core.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.ui.compose.Snackbar
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.compose.setSnackbarHostState
import com.spoonofcode.dojopro.core.ui.ext.addIf
import com.spoonofcode.dojopro.core.ui.ext.viewEnable
import com.spoonofcode.dojopro.core.ui.navigation.NavigationHandler
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

abstract class BaseScreen<VM : BaseViewModel<VS>, VS : BaseViewState>(
    open val screenTopAppBarTitle: StringResource? = null,
    open val backNavigationEnable: Boolean = true,
    open val verticalScrollEnable: Boolean = true,
    open val contentPadding: PaddingValues = PaddingValues(Paddings.screenPadding)
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
        val snackbarHostState = remember { SnackbarHostState() }
        val viewModel = provideViewModel()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        setSnackbarHostState(snackbarHostState, viewModel.snackbarEvent)

        ContentView(
            snackbarHostState = snackbarHostState,
            screenTopAppBarTitle = screenTopAppBarTitle,
            backNavigationEnable = backNavigationEnable,
            onBackClick = { navigator.pop() },
            isEnableView = viewState.isEnableView,
            isLoadingView = viewState.isLoadingView,
            content = provideContentView(viewModel, viewState),
        )
    }


    // TODO This functions should be private but now
    //  Android Studio will be support previews in commonMain
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ContentView(
        snackbarHostState: SnackbarHostState? = null,
        screenTopAppBarTitle: StringResource? = null,
        backNavigationEnable: Boolean = true,
        onBackClick: () -> Unit = {},
        isEnableView: Boolean = false,
        isLoadingView: Boolean = false,
        content: @Composable ColumnScope.() -> Unit,
    ) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState!!,
                    snackbar = { snackbarData -> Snackbar(snackbarData) }
                )
            },
            topBar = {
                if (screenTopAppBarTitle != null) {
                    CenterAlignedTopAppBar(
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
                        title = { Texts.TM(stringResource(resource = screenTopAppBarTitle)) },
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