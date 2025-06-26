package com.spoonofcode.dojopro.feature.appsettings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.message_fcm
import com.spoonofcode.dojopro.resources.search_user
import com.spoonofcode.dojopro.resources.settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal class AppSettingsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.settings,
) : BaseScreen<AppSettingsViewModel, AppSettingsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<AppSettingsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: AppSettingsViewModel,
        viewState: AppSettingsViewState
    ): @Composable ColumnScope.() -> Unit {
        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            navigateToSearchUser = { viewModel.navigateToSearchUser() },
            navigateToMessageFCM = { viewModel.navigateToMessageFCM() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: AppSettingsViewState,
        navigateToSearchUser: () -> Unit,
        navigateToMessageFCM: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            if (viewState.isSearchUserButtonVisible) {
                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.search_user),
                    onClick = navigateToSearchUser
                )
            }
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.message_fcm),
                onClick = navigateToMessageFCM
            )
        }
    }
}