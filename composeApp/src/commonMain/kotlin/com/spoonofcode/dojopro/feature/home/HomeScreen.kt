package com.spoonofcode.dojopro.feature.home

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
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.ui.Dimens
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.CarouselSportEventItem
import com.spoonofcode.dojopro.core.ui.compose.Carousels
import com.spoonofcode.dojopro.core.ui.compose.LoadingView
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditScreen
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.create_event
import com.spoonofcode.dojopro.resources.events_created_by_me
import com.spoonofcode.dojopro.resources.events_i_participated_in
import org.jetbrains.compose.resources.stringResource

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator: Navigator = LocalNavigator.currentOrThrow
        val viewModel = koinViewModel<HomeViewModel>()
        val viewState by viewModel.viewState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.initView()
        }

        ContentView(
            viewState = viewState,
            createSportEvent = {
                navigator.push(SportEventEditScreen())
            },
            goToMyEvent = {
                navigator.push(SportEventDetailsScreen(it))
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    internal fun ContentView(
        viewState: HomeViewState,
        createSportEvent: () -> Unit,
        goToMyEvent: (Int) -> Unit,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Home") },
                )
            }
        ) { innerPadding ->
            if (viewState.isViewLoading) {
                LoadingView()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState())
                ) {
                    val sportEventsUserParticipatedIn =
                        mapSportEventsToItems(viewState.sportEventsUserParticipatedIn)

                    val sportEventsCreatedByUser =
                        mapSportEventsToItems(viewState.sportEventsCreatedByUser)

                    Carousels.CustomCarousel(
                        title = stringResource(resource = Res.string.events_i_participated_in),
                        items = sportEventsUserParticipatedIn,
                        onItemClick = { goToMyEvent(it) }
                    )

                    if (sportEventsCreatedByUser.isNotEmpty()) {
                        Carousels.CustomCarousel(
                            title = stringResource(resource = Res.string.events_created_by_me),
                            items = sportEventsCreatedByUser,
                            onItemClick = { goToMyEvent(it) }
                        )
                    }

                    Column(
                        modifier = Modifier.padding(all = Dimens.screenPadding)
                    ) {
                        Buttons.PrimaryButton(
                            text = stringResource(resource = Res.string.create_event),
                            onClick = createSportEvent
                        )
                    }
                }
            }
        }
    }

    private fun mapSportEventsToItems(sportEvents: List<SportEvent>) =
        sportEvents.map { sportEvent ->
            CarouselSportEventItem(
                sportEventId = sportEvent.id,
                title = sportEvent.title,
                startEventDateTime = sportEvent.startDateTime
            )
        }

}