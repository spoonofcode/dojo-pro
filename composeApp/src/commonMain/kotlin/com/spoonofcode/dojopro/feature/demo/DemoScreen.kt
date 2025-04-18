package com.spoonofcode.dojopro.feature.demo

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.compose_multiplatform
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
            AppLoadingOverlay(iconRes = Res.drawable.compose_multiplatform.hashCode())
        }
    }

    @Preview
    @Composable
    private fun DemoScreenPreview() {
        DemoScreen()
    }
}