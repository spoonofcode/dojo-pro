package tabs.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.settings
import core.ui.ext.koinViewModel
import org.jetbrains.compose.resources.stringResource

class SettingsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SettingsViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        ContentView(
            viewState = viewState,
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: SettingsViewState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(resource = Res.string.settings)) },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
                    .padding(innerPadding)
            ) {
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
}