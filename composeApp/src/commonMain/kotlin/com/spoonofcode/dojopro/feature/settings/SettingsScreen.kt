package com.spoonofcode.dojopro.feature.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
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
        return ContentView(
            viewState = viewState,
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SettingsViewState,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Text(
                text = viewState.title,
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = stringResource(resource = Res.string.settings),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Switch Language",
                    modifier = Modifier.clickable {

                    }.padding(bottom = 8.dp)
                )
            }
        }
    }
}