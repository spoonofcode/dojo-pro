package com.spoonofcode.dojopro.feature.shop

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.shop
import org.jetbrains.compose.resources.StringResource

internal class ShopScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.shop
) : BaseScreen<ShopViewModel, ShopViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<ShopViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: ShopViewModel,
        viewState: ShopViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,

            )
    }

    @Composable
    private fun ContentView(
        viewState: ShopViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Text(viewState.title)
        }
    }
}