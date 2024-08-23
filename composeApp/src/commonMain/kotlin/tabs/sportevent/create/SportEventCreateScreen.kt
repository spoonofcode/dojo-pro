package tabs.sportevent.create

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
import cafe.adriel.voyager.core.screen.Screen
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.cost
import com.spoonofcode.dojopro.resources.description
import com.spoonofcode.dojopro.resources.end
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.number_of_people
import com.spoonofcode.dojopro.resources.room
import com.spoonofcode.dojopro.resources.sport_event
import com.spoonofcode.dojopro.resources.start
import com.spoonofcode.dojopro.resources.submit
import com.spoonofcode.dojopro.resources.title
import core.ui.Dimens
import core.ui.compose.Buttons
import core.ui.compose.DatePickers
import core.ui.compose.DropDownMenus
import core.ui.compose.LoadingView
import core.ui.compose.Sliders
import core.ui.compose.Spacers
import core.ui.compose.TextFields
import core.ui.ext.koinViewModel
import org.jetbrains.compose.resources.stringResource

class SportEventCreateScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<SportEventCreateViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.initView()
        }

        ContentView(
            viewState = viewState,
            changeTitle = { viewModel.changeTitle(it) },
            changeCoach = { viewModel.changeCoach(it) }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: SportEventCreateViewState,
        changeTitle: (String) -> Unit,
        changeCoach: (String) -> Unit,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(Res.string.sport_event)) },
                )
            }
        ) { innerPadding ->
            if (viewState.isViewLoading) {
                LoadingView()
            } else {
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
                    DropDownMenus.DropdownMenu(
                        enabled = true,
                        label = stringResource(resource = Res.string.coach),
                        value = viewState.selectedCoach!!.fullName,
                        values = viewState.coaches.map { it.fullName },
                        onValueChange = { changeCoach(it) }
                    )
                    DropDownMenus.DropdownMenu(
                        enabled = true,
                        label = stringResource(resource = Res.string.room),
                        value = viewState.rooms.first().name,
                        values = viewState.rooms.map { it.name },
                        onValueChange = { }
                    )
                    DropDownMenus.DropdownMenu(
                        enabled = true,
                        label = stringResource(resource = Res.string.level),
                        value = viewState.levels.first().name,
                        values = viewState.levels.map { it.name },
                        onValueChange = { }
                    )

                    Sliders.RangeSlider(
                        label = stringResource(resource = Res.string.number_of_people),
                        minValue = 1f,
                        maxValue = 10f,
                        steps = 8,
                    )

                    TextFields.Outlined(
                        value = viewState.title,
                        onValueChange = { changeTitle(it) },
                        label = stringResource(resource = Res.string.cost),
                    )

                    DatePickers.DatePickerWithTimer(
                        label = stringResource(resource = Res.string.start),
                    )

                    DatePickers.DatePickerWithTimer(
                        label = stringResource(resource = Res.string.end),
                    )

                    Spacers.Weight1(this)

                    Spacers.VerticalBetweenFields()
                    Buttons.PrimaryButton(
                        text = stringResource(resource = Res.string.submit),
                        onClick = {}
                    )
                    Spacers.BottomSpace()
                }
            }
        }
    }
}