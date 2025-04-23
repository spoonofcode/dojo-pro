package com.spoonofcode.dojopro.feature.home

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.model.Club
import com.spoonofcode.dojopro.core.model.Level
import com.spoonofcode.dojopro.core.model.Room
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.Type
import com.spoonofcode.dojopro.core.model.Types
import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.Paddings
import com.spoonofcode.dojopro.core.ui.compose.CarouselSportEventItem
import com.spoonofcode.dojopro.core.ui.compose.Carousels
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.create_event
import com.spoonofcode.dojopro.resources.dojo_advanced_group_training
import com.spoonofcode.dojopro.resources.dojo_beginners_group_training
import com.spoonofcode.dojopro.resources.dojo_beginners_training
import com.spoonofcode.dojopro.resources.dojo_boxing_training
import com.spoonofcode.dojopro.resources.dojo_grappling_training
import com.spoonofcode.dojopro.resources.dojo_individual_training
import com.spoonofcode.dojopro.resources.dojo_mat_training
import com.spoonofcode.dojopro.resources.dojo_motor_training
import com.spoonofcode.dojopro.resources.dojo_open_training
import com.spoonofcode.dojopro.resources.dojo_training
import com.spoonofcode.dojopro.resources.dojo_youth_training
import com.spoonofcode.dojopro.resources.events_created_by_me
import com.spoonofcode.dojopro.resources.events_i_participated_in
import com.spoonofcode.dojopro.resources.home
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class HomeScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.home,
    override val backNavigationEnable: Boolean = false,
    override val contentPadding: PaddingValues = PaddingValues(
        start = Paddings.screenPadding,
        top = Paddings.screenPadding,
        bottom = Paddings.screenPadding,
    )
) : BaseScreen<HomeViewModel, HomeViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<HomeViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: HomeViewModel,
        viewState: HomeViewState
    ): @Composable ColumnScope.() -> Unit {

        super.topBarActions = listOf(
            TopBarAction(
                icon = Icons.Default.Add,
                description = stringResource(resource = Res.string.create_event),
                onClick = { viewModel.goToCreateSportEvent() }
            ),
        )

        super.reloadScreen = { viewModel.initView() }

        return ContentView(
            viewState = viewState,
            goToMyEvent = { viewModel.goToMyEvent(it) },
        )
    }

    @Composable
    private fun ContentView(
        viewState: HomeViewState,
        goToMyEvent: (Int) -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            val sportEventsUserParticipatedIn =
                mapSportEventsToItems(viewState.sportEventsUserParticipatedIn)

            val sportEventsCreatedByUser =
                mapSportEventsToItems(viewState.sportEventsCreatedByUser)

            if (sportEventsUserParticipatedIn.isNotEmpty()) {
                Carousels.CustomCarousel(
                    title = stringResource(resource = Res.string.events_i_participated_in),
                    items = sportEventsUserParticipatedIn,
                    onItemClick = { goToMyEvent(it) }
                )
            }

            if (sportEventsCreatedByUser.isNotEmpty()) {
                Carousels.CustomCarousel(
                    title = stringResource(resource = Res.string.events_created_by_me),
                    items = sportEventsCreatedByUser,
                    onItemClick = { goToMyEvent(it) }
                )
            }
        }
    }

    private fun mapSportEventsToItems(sportEvents: List<SportEvent>) =
        sportEvents.map { sportEvent ->
            CarouselSportEventItem(
                sportEventId = sportEvent.id,
                title = sportEvent.title,
                rangeDateTime = sportEvent.formatRangeWithDurationInMinutes(),
                imageResId = when (sportEvent.type.id) {
                    Types.DOJO_BOXING_TRAINING.id -> Res.drawable.dojo_boxing_training
                    Types.DOJO_GRAPPLING_TRAINING.id -> Res.drawable.dojo_grappling_training
                    Types.DOJO_YOUTH_TRAINING.id -> Res.drawable.dojo_youth_training
                    Types.DOJO_BEGINNERS_GROUP_TRAINING.id -> Res.drawable.dojo_beginners_group_training
                    Types.DOJO_BEGINNERS_TRAINING.id -> Res.drawable.dojo_beginners_training
                    Types.DOJO_INDIVIDUAL_TRAINING.id -> Res.drawable.dojo_individual_training
                    Types.DOJO_MOTOR_TRAINING.id -> Res.drawable.dojo_motor_training
                    Types.DOJO_OPEN_TRAINING.id -> Res.drawable.dojo_open_training
                    Types.DOJO_MAT_TRAINING.id -> Res.drawable.dojo_mat_training
                    Types.DOJO_ADVANCED_GROUP_TRAINING.id -> Res.drawable.dojo_advanced_group_training
                    else -> Res.drawable.dojo_training
                }
            )
        }

    // region previews
    // TODO This functions should be private and with @Preview annotation but now
    //  Android Studio will be support previews in commonMain
    @Composable
    fun InitializedHomeScreenPreview() {
        ContentView(
            content = ContentView(
                viewState = HomeViewState(
                    sportEventsUserParticipatedIn = listOf(
                        SportEvent(
                            id = 1,
                            title = "test 1",
                            description = "Trenten",
                            minNumberOfPeople = 4078,
                            maxNumberOfPeople = 3999,
                            cost = "Abbe",
                            startDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            endDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            club = Club(
                                id = 1,
                                name = "Club 1",
                                location = "Wroclaw",
                            ),
                            room = Room(
                                id = 1,
                                name = "Test",
                            ),
                            type = Type(
                                id = 1,
                                name = "Test",
                            ),
                            level = Level(
                                id = 1,
                                name = "Test",
                            ),
                            creatorUser = User(
                                id = 1,
                                firstName = "Bartosz",
                                lastName = "Luczak",
                                nickName = "Lycha",
                                email = "Test",
                            ),
                        ),
                        SportEvent(
                            id = 1,
                            title = "test 1",
                            description = "Trenten",
                            minNumberOfPeople = 4078,
                            maxNumberOfPeople = 3999,
                            cost = "Abbe",
                            startDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            endDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            club = Club(
                                id = 1,
                                name = "Club 1",
                                location = "Wroclaw",
                            ),
                            room = Room(
                                id = 1,
                                name = "Test",
                            ),
                            type = Type(
                                id = 1,
                                name = "Test",
                            ),
                            level = Level(
                                id = 1,
                                name = "Test",
                            ),
                            creatorUser = User(
                                id = 1,
                                firstName = "Test",
                                lastName = "Test",
                                email = "Test",
                            )
                        ),
                        SportEvent(
                            id = 1,
                            title = "test 1",
                            description = "Trenten",
                            minNumberOfPeople = 4078,
                            maxNumberOfPeople = 3999,
                            cost = "Abbe",
                            startDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            endDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            club = Club(
                                id = 1,
                                name = "Club 1",
                                location = "Wroclaw",
                            ),
                            room = Room(
                                id = 1,
                                name = "Test",
                            ),
                            type = Type(
                                id = 1,
                                name = "Test",
                            ),
                            level = Level(
                                id = 1,
                                name = "Test",
                            ),
                            creatorUser = User(
                                id = 1,
                                firstName = "Bartosz",
                                lastName = "Luczak",
                                nickName = "Lycha",
                                email = "Test",
                            ),
                        ),
                        SportEvent(
                            id = 1,
                            title = "test 1",
                            description = "Trenten",
                            minNumberOfPeople = 4078,
                            maxNumberOfPeople = 3999,
                            cost = "Abbe",
                            startDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            endDateTime = LocalDateTime(2023, 1, 1, 1, 1),
                            club = Club(
                                id = 1,
                                name = "Club 1",
                                location = "Wroclaw",
                            ),
                            room = Room(
                                id = 1,
                                name = "Test",
                            ),
                            type = Type(
                                id = 1,
                                name = "Test",
                            ),
                            level = Level(
                                id = 1,
                                name = "Test",
                            ),
                            creatorUser = User(
                                id = 1,
                                firstName = "Test",
                                lastName = "Test",
                                email = "Test",
                            )
                        ),
                    ),
                ),
                goToMyEvent = {},
            )
        )
    }
    // endregion
}