package com.spoonofcode.dojopro.feature.appsettings

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.delete_account
import com.spoonofcode.dojopro.resources.message_fcm
import com.spoonofcode.dojopro.resources.nfc
import com.spoonofcode.dojopro.resources.search_user
import com.spoonofcode.dojopro.resources.settings
import com.spoonofcode.dojopro.resources.sign_out
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
            navigateToNFC = { viewModel.navigateToNFC() },
            signOut = { viewModel.signOut() },
            deleteAccount = { viewModel.deleteAccount() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: AppSettingsViewState,
        navigateToSearchUser: () -> Unit,
        navigateToMessageFCM: () -> Unit,
        navigateToNFC: () -> Unit,
        signOut: () -> Unit,
        deleteAccount: () -> Unit,
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

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.nfc),
                onClick = navigateToNFC
            )

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.sign_out),
                onClick = signOut
            )

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.delete_account),
                onClick = deleteAccount
            )
        }
    }
}