package com.spoonofcode.dojopro.feature.search.filter

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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.dojopro.core.ui.Dimens
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.DropDownMenus
import com.spoonofcode.dojopro.core.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.core.ui.navigation.NavigationHandler
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.applyFilter
import com.spoonofcode.dojopro.resources.coach
import com.spoonofcode.dojopro.resources.level
import com.spoonofcode.dojopro.resources.sport_event
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource

class FilterScreen : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<FilterViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        NavigationHandler(
            navigationFlow = viewModel.navigationFlow,
            navigator = navigator
        )

        LaunchedEffect(Unit) {
            viewModel.initView()
        }

        ContentView(
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
                    Spacers.BottomSpace()
                }
            }
        }
    }
}