package com.spoonofcode.dojopro.feature.settings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.search_user
import com.spoonofcode.dojopro.resources.settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class SettingsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.settings,
) : BaseScreen<SettingsViewModel, SettingsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<SettingsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: SettingsViewModel,
        viewState: SettingsViewState
    ): @Composable ColumnScope.() -> Unit {
        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            navigateToUpdateUsers = { viewModel.navigateToSearchUser() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SettingsViewState,
        navigateToUpdateUsers: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            if (viewState.isSearchUserButtonVisible) {
                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.search_user),
                    onClick = navigateToUpdateUsers
                )
            }
        }
    }
}