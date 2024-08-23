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
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource

class SportEventCreateScreen : Screen {

    companion object {
        private const val PEOPLE_RANGE_MIN_VALUE = 1f
        private const val PEOPLE_RANGE_MAX_VALUE = 10f
        private const val PEOPLE_RANGE_NUMBER_OF_STEPS = 8
    }

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
            changeDescription = { viewModel.changeDescription(it) },
            changeCoach = { viewModel.changeCoach(it) },
            changeRoom = { viewModel.changeRoom(it) },
            changeLevel = { viewModel.changeLevel(it) },
            changeMinNumberOfPeople = { viewModel.changeMinNumberOfPeople(it) },
            changeMaxNumberOfPeople = { viewModel.changeMaxNumberOfPeople(it) },
            changeCost = { viewModel.changeCost(it) },
            changeStartDateTime = { viewModel.changeStartDateTime(it) },
            changeEndDateTime = { viewModel.changeEndDateTime(it) },
            createSportEvent = { viewModel.createSportEvent() },
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: SportEventCreateViewState,
        changeTitle: (String) -> Unit,
        changeDescription: (String) -> Unit,
        changeCoach: (Int) -> Unit,
        changeRoom: (Int) -> Unit,
        changeLevel: (Int) -> Unit,
        changeMinNumberOfPeople: (Int) -> Unit,
        changeMaxNumberOfPeople: (Int) -> Unit,
        changeCost: (String) -> Unit,
        changeStartDateTime: (LocalDateTime) -> Unit,
        changeEndDateTime: (LocalDateTime) -> Unit,
        createSportEvent: () -> Unit,
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
                        value = viewState.description,
                        onValueChange = { changeDescription(it) },
                        label = stringResource(resource = Res.string.description),
                    )
                    DropDownMenus.DropdownMenu(
                        enabled = true,
                        label = stringResource(resource = Res.string.coach),
                        value = viewState.coaches.getValue(viewState.selectedCoachId!!),
                        values = viewState.coaches,
                        onValueChange = { changeCoach(it) }
                    )
                    DropDownMenus.DropdownMenu(
                        enabled = true,
                        label = stringResource(resource = Res.string.room),
                        value = viewState.rooms.getValue(viewState.selectedRoomId!!),
                        values = viewState.rooms,
                        onValueChange = { changeRoom(it) }
                    )
                    DropDownMenus.DropdownMenu(
                        enabled = true,
                        label = stringResource(resource = Res.string.level),
                        value = viewState.levels.getValue(viewState.selectedLevelId!!),
                        values = viewState.levels,
                        onValueChange = { changeLevel(it) }
                    )

                    Sliders.RangeSlider(
                        label = stringResource(resource = Res.string.number_of_people),
                        minValue = PEOPLE_RANGE_MIN_VALUE,
                        maxValue = PEOPLE_RANGE_MAX_VALUE,
                        steps = PEOPLE_RANGE_NUMBER_OF_STEPS,
                        selectedStartPosition = viewState.selectedMinNumberOfPeople.toFloat(),
                        selectedEndPosition = viewState.selectedMaxNumberOfPeople.toFloat(),
                        onStartPositionChange = { changeMinNumberOfPeople(it) },
                        onEndPositionChange = { changeMaxNumberOfPeople(it) },
                    )

                    TextFields.Outlined(
                        label = stringResource(resource = Res.string.cost),
                        value = viewState.cost,
                        onValueChange = { changeCost(it) },
                    )

                    DatePickers.DatePickerWithTimer(
                        label = stringResource(resource = Res.string.start),
                        value = viewState.startDateTime,
                        onValueChange = {
                            changeStartDateTime(it)
                        },
                    )

                    DatePickers.DatePickerWithTimer(
                        label = stringResource(resource = Res.string.end),
                        value = viewState.endDateTime,
                        onValueChange = {
                            changeEndDateTime(it)
                        },
                    )

                    Spacers.Weight1(this)

                    Spacers.VerticalBetweenFields()
                    Buttons.PrimaryButton(
                        text = stringResource(resource = Res.string.submit),
                        onClick = createSportEvent
                    )
                    Spacers.BottomSpace()
                }
            }
        }
    }
}