package tabs.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.create_event
import com.spoonofcode.dojopro.resources.my_events
import core.ui.Dimens
import core.ui.compose.Buttons
import core.ui.compose.CarouselSportEventItem
import core.ui.compose.Carousels
import core.ui.compose.LoadingView
import core.ui.ext.koinViewModel
import org.jetbrains.compose.resources.stringResource
import tabs.sportevent.create.SportEventCreateScreen
import tabs.sportevent.details.SportEventDetailsScreen

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
                navigator.push(SportEventCreateScreen())
            },
            goToMyEvent = {
                navigator.push(SportEventDetailsScreen(sportEventId = 1))
            }
        )
    }

    @Composable
    internal fun ContentView(
        viewState: HomeViewState,
        createSportEvent: () -> Unit,
        goToMyEvent: () -> Unit,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = Dimens.screenPadding)
        ) {
            if (viewState.isViewLoading) {
                LoadingView()
            } else {
                val items =
                    viewState.sportEvents.map {
                        CarouselSportEventItem(title = it.title)
                    }

                Carousels.CustomCarousel(
                    title = stringResource(resource = Res.string.my_events),
                    items = items,
                    onItemClick = goToMyEvent
                )

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