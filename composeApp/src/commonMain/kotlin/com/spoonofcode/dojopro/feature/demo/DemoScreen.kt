package com.spoonofcode.dojopro.feature.demo

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.demo
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class DemoScreen(
    override val backNavigationEnable: Boolean = false,
    override val verticalScrollEnable: Boolean = false,
) : BaseScreen<DemoViewModel, DemoViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<DemoViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: DemoViewModel,
        viewState: DemoViewState,
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView()
        }

        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    internal fun ContentView(
        viewState: DemoViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.BL(text = stringResource(resource = Res.string.demo))
        }
    }

    @Preview
    @Composable
    private fun DemoScreenPreview() {
        DemoScreen()
    }
}