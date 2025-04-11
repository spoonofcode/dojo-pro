package com.spoonofcode.dojopro.feature.user

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.search_user
import com.spoonofcode.dojopro.resources.update_users
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class SearchUserScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.search_user,
) : BaseScreen<SearchUserViewModel, SearchUserViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<SearchUserViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: SearchUserViewModel,
        viewState: SearchUserViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            navigateToUpdateUsers = { viewModel.navigateToUpdateUsers() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SearchUserViewState,
        navigateToUpdateUsers: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.update_users),
                onClick = navigateToUpdateUsers
            )
        }
    }
}