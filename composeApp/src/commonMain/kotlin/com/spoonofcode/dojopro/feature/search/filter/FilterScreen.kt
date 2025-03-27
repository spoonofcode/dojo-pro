package com.spoonofcode.dojopro.feature.search.filter

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.DropDownMenus
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.applyFilter
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.sport_event
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class FilterScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.sport_event,
) : BaseScreen<FilterViewModel, FilterViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<FilterViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: FilterViewModel,
        viewState: FilterViewState
    ): @Composable ColumnScope.() -> Unit {
        return ContentView(
            viewState = viewState,
            changeCoach = { viewModel.changeCoach(it) },
            changeLevel = { viewModel.changeLevel(it) },
            changeStartDateTime = { viewModel.changeStartDateTime(it) },
            changeEndDateTime = { viewModel.changeEndDateTime(it) },
            applyFilter = { viewModel.applyFilter() },
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: FilterViewState,
        changeCoach: (Int) -> Unit,
        changeLevel: (Int) -> Unit,
        changeStartDateTime: (LocalDateTime) -> Unit,
        changeEndDateTime: (LocalDateTime) -> Unit,
        applyFilter: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
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

//                    DatePickers.DatePickerWithTimer(
//                        label = stringResource(resource = Res.string.start),
//                        value = viewState.startDateTime,
//                        onValueChange = {
//                            changeStartDateTime(it)
//                        },
//                    )
//
//                    DatePickers.DatePickerWithTimer(
//                        label = stringResource(resource = Res.string.end),
//                        value = viewState.endDateTime,
//                        onValueChange = {
//                            changeEndDateTime(it)
//                        },
//                    )

            Spacers.Weight1(this)

            Spacers.VerticalBetweenFields()

            Buttons.PrimaryButton(
                text = stringResource(resource = Res.string.applyFilter),
                onClick = { applyFilter() }
            )
        }
    }
}