package com.spoonofcode.dojopro.feature.user.detail

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.feature.user.search.SearchUserViewModel
import com.spoonofcode.dojopro.feature.user.search.SearchUserViewState
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.search_user
import org.jetbrains.compose.resources.StringResource

internal class UserDetailScreen(
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
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SearchUserViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
        }
    }
}