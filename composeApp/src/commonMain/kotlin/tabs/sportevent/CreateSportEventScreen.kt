package tabs.sportevent

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
import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.cost
import com.spoonofcode.dojopro.resources.description
import com.spoonofcode.dojopro.resources.end
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.max_number_of_people
import com.spoonofcode.dojopro.resources.min_number_of_people
import com.spoonofcode.dojopro.resources.room
import com.spoonofcode.dojopro.resources.start
import com.spoonofcode.dojopro.resources.submit
import com.spoonofcode.dojopro.resources.title
import com.spoonofcode.dojopro.resources.type
import core.ui.Dimens
import core.ui.compose.Buttons
import core.ui.compose.DropdownMenu
import core.ui.compose.Spacers
import core.ui.compose.TextFields
import core.ui.ext.koinViewModel
import fakeData.getFakeCoaches
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

class CreateSportEventScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<CreateSportEventViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        ContentView(
            viewState = viewState,
            changeTitle = { viewModel.changeTitle(it) }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: CreateSportEventViewState,
        changeTitle: (String) -> Unit,
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
                    .padding(Dimens.screenPadding)
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.title),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.description),
                )
                DropdownMenu(
                    enabled = true,
                    label = stringResource(resource = Res.string.coach),
                    value = getFakeCoaches().first().fullName,
                    values = getFakeCoaches().map { it.fullName },
                    onValueChange = { }
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.room),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.level),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.type),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.cost),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.start),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.end),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.max_number_of_people),
                )
                TextFields.Outlined(
                    value = viewState.title,
                    onValueChange = { changeTitle(it) },
                    label = stringResource(resource = Res.string.min_number_of_people),
                )

                Spacers.Weight1(this)

                Spacers.BetweenFields()
                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.submit),
                    onClick = {}
                )
                Spacers.BottomSpace()

            }
        }
    }
}

// region Previews
@Composable
private fun PreviewContentView(viewState: CreateSportEventViewState = CreateSportEventViewState()) {
    CreateSportEventScreen().ContentView(
        viewState = viewState,
        changeTitle = {}
    )
}

@Composable
@Preview
private fun SportEventScreenPreview() {
    PreviewContentView()
}
// endregion