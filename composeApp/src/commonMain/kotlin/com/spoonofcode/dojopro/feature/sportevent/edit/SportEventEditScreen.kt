package com.spoonofcode.dojopro.feature.sportevent.edit

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.DatePickers
import com.spoonofcode.dojopro.core.ui.compose.DropDownMenus
import com.spoonofcode.dojopro.core.ui.compose.Sliders
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.TextFields
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.club
import com.spoonofcode.dojopro.resources.cost
import com.spoonofcode.dojopro.resources.create_sport_event
import com.spoonofcode.dojopro.resources.description
import com.spoonofcode.dojopro.resources.edit_sport_event
import com.spoonofcode.dojopro.resources.end
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.number_of_people
import com.spoonofcode.dojopro.resources.pln
import com.spoonofcode.dojopro.resources.room
import com.spoonofcode.dojopro.resources.start
import com.spoonofcode.dojopro.resources.submit
import com.spoonofcode.dojopro.resources.title
import com.spoonofcode.dojopro.resources.type
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class SportEventEditScreen(
    private val screenMode: ScreenMode = ScreenMode.Create,
    override val screenTopAppBarTitle: StringResource = if (screenMode == ScreenMode.Create) {
        Res.string.create_sport_event
    } else {
        Res.string.edit_sport_event
    },
) : BaseScreen<SportEventEditViewModel, SportEventEditViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<SportEventEditViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: SportEventEditViewModel,
        viewState: SportEventEditViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView(screenMode)
        }

        super.reloadScreen = { viewModel.initView(screenMode) }

        return ContentView(
            viewState = viewState,
            changeTitle = { viewModel.changeTitle(it) },
            changeDescription = { viewModel.changeDescription(it) },
            changeClub = { viewModel.changeClub(it) },
            changeRoom = { viewModel.changeRoom(it) },
            changeLevel = { viewModel.changeLevel(it) },
            changeType = { viewModel.changeType(it) },
            changeMinNumberOfPeople = { viewModel.changeMinNumberOfPeople(it) },
            changeMaxNumberOfPeople = { viewModel.changeMaxNumberOfPeople(it) },
            changeCost = { viewModel.changeCost(it) },
            changeStartDateTime = { viewModel.changeStartDateTime(it) },
            changeEndDateTime = { viewModel.changeEndDateTime(it) },
            submitSportEvent = { viewModel.submitSportEvent() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: SportEventEditViewState,
        changeTitle: (String) -> Unit,
        changeDescription: (String) -> Unit,
        changeClub: (Int) -> Unit,
        changeRoom: (Int) -> Unit,
        changeLevel: (Int) -> Unit,
        changeType: (Int) -> Unit,
        changeMinNumberOfPeople: (Int) -> Unit,
        changeMaxNumberOfPeople: (Int) -> Unit,
        changeCost: (Int) -> Unit,
        changeStartDateTime: (LocalDateTime) -> Unit,
        changeEndDateTime: (LocalDateTime) -> Unit,
        submitSportEvent: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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
                label = stringResource(resource = Res.string.club),
                value = viewState.clubs.getValue(viewState.selectedClubId!!),
                values = viewState.clubs,
                onValueChange = { changeClub(it) }
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
            DropDownMenus.DropdownMenu(
                enabled = true,
                label = stringResource(resource = Res.string.type),
                value = viewState.types.getValue(viewState.selectedTypeId!!),
                values = viewState.types,
                onValueChange = { changeType(it) }
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

            Sliders.Slider(
                labelPrefix = stringResource(resource = Res.string.cost),
                labelPostfix = stringResource(resource = Res.string.pln),
                minValue = COST_MIN_VALUE,
                maxValue = COST_MAX_VALUE,
                steps = COST_NUMBER_OF_STEPS,
                selectedPosition = viewState.selectedCost.toFloat(),
                onPositionChange = { changeCost(it) }
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
                onClick = submitSportEvent
            )
        }
    }

    companion object {
        private const val PEOPLE_RANGE_MIN_VALUE = 1f
        private const val PEOPLE_RANGE_MAX_VALUE = 20f
        private const val PEOPLE_RANGE_NUMBER_OF_STEPS = 18
        private const val COST_MIN_VALUE = 100f
        private const val COST_MAX_VALUE = 300f
        private const val COST_NUMBER_OF_STEPS = 7
    }
}