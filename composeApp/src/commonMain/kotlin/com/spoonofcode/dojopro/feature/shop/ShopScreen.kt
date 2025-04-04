package com.spoonofcode.dojopro.feature.shop

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.MyWebView
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.shop
import org.jetbrains.compose.resources.StringResource

internal class ShopScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.shop,
    override val backNavigationEnable: Boolean = false,
    override val contentPadding: PaddingValues = PaddingValues()
) : BaseScreen<ShopViewModel, ShopViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ShopViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ShopViewModel,
        viewState: ShopViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(viewState = viewState)
    }

    @Composable
    private fun ContentView(
        viewState: ShopViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            MyWebView(url = viewState.url)
        }
    }
}