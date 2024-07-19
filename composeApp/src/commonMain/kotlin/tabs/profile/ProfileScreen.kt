package tabs.profile

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import core.ui.ext.koinViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

class ProfileScreen : Screen {


    @Composable
    override fun Content() {
        val viewModel = koinViewModel<ProfileViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        LaunchedEffect(viewModel) {
            viewModel.updateTasks()
        }
        ContentView(
            viewState = viewState,
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: ProfileViewState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Profile") },
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

                viewState.profile?.let {
                    Text(
                        text = it.firstName,
                    )
                }

                viewState.profile?.let {
                    Text(
                        text = it.lastName,
                    )
                }
            }
        }
    }
}

// region Previews
@Composable
private fun PreviewContentView(viewState: ProfileViewState = ProfileViewState()) {
    ProfileScreen().ContentView(viewState)
}


@Composable
@Preview
private fun ProfileScreenPreview() {
    PreviewContentView()
}
// endregion