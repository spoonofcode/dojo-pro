package tabs.sportevent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.coach
import core.ui.Dimens
import core.ui.ext.koinViewModel
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

class CreateSportEventScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CreateSportEventViewModel>()
        val viewState by viewModel.viewState.collectAsState()

//        LaunchedEffect(viewModel) {
//            viewModel.updateTasks()
//        }
        ContentView(
            viewState = viewState,
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: CreateSportEventViewState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("SportEvent") },
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(Dimens.screenPadding)
                    .padding(innerPadding)
            ) {
                FormScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, InternalResourceApi::class)
@Composable
fun FormScreen() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var coach by remember { mutableStateOf("") }
    var room by remember { mutableStateOf("") }
    var level by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var cost by remember { mutableStateOf("") }
    var startDateTime by remember { mutableStateOf("") }
    var endDateTime by remember { mutableStateOf("") }
    var maxNumberOfPeople by remember { mutableStateOf("") }
    var minNumberOfPeople by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

//        Text(
//            "Example text",
//            style = MaterialTheme.typography.headlineSmall
//        )
//
        Text(text = stringResource(resource = Res.string.coach))
//
//        DropdownMenu(
//            enabled = true,
//            label = stringResource(id = R.string.greeting),
//            value = "Gordon",
//            values = listOf(),
//            onValueChange = { -> }
//
//        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = coach,
            onValueChange = { coach = it },
            label = { Text("Coach") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = room,
            onValueChange = { room = it },
            label = { Text("Room") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = level,
            onValueChange = { level = it },
            label = { Text("Level") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Type") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = cost,
            onValueChange = { cost = it },
            label = { Text("Cost") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = startDateTime,
            onValueChange = { startDateTime = it },
            label = { Text("Start DateTime") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = endDateTime,
            onValueChange = { endDateTime = it },
            label = { Text("End DateTime") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = maxNumberOfPeople,
            onValueChange = { maxNumberOfPeople = it },
            label = { Text("Max Number of People") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = minNumberOfPeople,
            onValueChange = { minNumberOfPeople = it },
            label = { Text("Min Number of People") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Handle form submission
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Submit")
        }
    }
}

// region Previews
@Composable
private fun PreviewContentView(viewState: CreateSportEventViewState = CreateSportEventViewState()) {
    CreateSportEventScreen().ContentView(viewState)
}


@Composable
@Preview
private fun SportEventScreenPreview() {
    PreviewContentView()
}
// endregion