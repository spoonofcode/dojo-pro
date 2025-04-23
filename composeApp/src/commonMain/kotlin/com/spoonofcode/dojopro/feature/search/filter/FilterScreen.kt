package com.spoonofcode.dojopro.feature.search.filter

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.DatePickers
import com.spoonofcode.dojopro.core.ui.compose.DropDownMenus
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.apply_filter
import com.spoonofcode.dojopro.resources.club
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.end
import com.spoonofcode.dojopro.resources.filter_sport_events
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.start
import com.spoonofcode.dojopro.resources.type
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class FilterScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.filter_sport_events,
) : BaseScreen<FilterViewModel, FilterViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<FilterViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: FilterViewModel,
        viewState: FilterViewState
    ): @Composable ColumnScope.() -> Unit {
        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            changeClub = { viewModel.changeClub(it) },
            changeCoach = { viewModel.changeCoach(it) },
            changeLevel = { viewModel.changeLevel(it) },
            changeType = { viewModel.changeType(it) },
            changeStartDateTime = { viewModel.changeStartDateTime(it) },
            changeEndDateTime = { viewModel.changeEndDateTime(it) },
            applyFilter = { viewModel.applyFilter() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: FilterViewState,
        changeClub: (Int) -> Unit,
        changeCoach: (Int) -> Unit,
        changeLevel: (Int) -> Unit,
        changeType: (Int) -> Unit,
        changeStartDateTime: (LocalDateTime) -> Unit,
        changeEndDateTime: (LocalDateTime) -> Unit,
        applyFilter: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            DropDownMenus.DropdownMenu(
                enabled = true,
                label = stringResource(resource = Res.string.club),
                value = viewState.clubs.getValue(viewState.selectedClubId),
                values = viewState.clubs,
                onValueChange = { changeClub(it) }
            )
            DropDownMenus.DropdownMenu(
                enabled = true,
                label = stringResource(resource = Res.string.coach),
                value = viewState.coaches.getValue(viewState.selectedCoachId),
                values = viewState.coaches,
                onValueChange = { changeCoach(it) }
            )
            DropDownMenus.DropdownMenu(
                enabled = true,
                label = stringResource(resource = Res.string.level),
                value = viewState.levels.getValue(viewState.selectedLevelId),
                values = viewState.levels,
                onValueChange = { changeLevel(it) }
            )

            DropDownMenus.DropdownMenu(
                enabled = true,
                label = stringResource(resource = Res.string.type),
                value = viewState.types.getValue(viewState.selectedTypeId),
                values = viewState.types,
                onValueChange = { changeType(it) }
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
                text = stringResource(resource = Res.string.apply_filter),
                onClick = { applyFilter() }
            )
        }
    }
}