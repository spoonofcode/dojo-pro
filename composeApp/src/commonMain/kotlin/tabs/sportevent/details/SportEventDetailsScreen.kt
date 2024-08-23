package tabs.sportevent.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.dojo_room
import core.ui.Dimens
import core.ui.compose.Spacers
import core.ui.ext.koinViewModel
import org.jetbrains.compose.resources.painterResource

data class SportEventDetailsScreen(
    val sportEventId: Int,
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SportEventDetailsViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.initView(sportEventId = sportEventId)
        }

        ContentView(
            viewState = viewState,
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: SportEventDetailsViewState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("SportEventDetails") },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.screenPadding)
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.dojo_room),
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "This is a sample text below the image",
                )

                Spacers.BottomSpace()

            }
        }
    }
}