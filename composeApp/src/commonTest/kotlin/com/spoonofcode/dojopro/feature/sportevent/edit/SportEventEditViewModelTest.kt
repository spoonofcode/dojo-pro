package com.spoonofcode.dojopro.feature.sportevent.edit

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.ClubMockData.CLUBS
import com.spoonofcode.dojopro.core.data.mockdata.LevelMockData.LEVELS
import com.spoonofcode.dojopro.core.data.mockdata.RoomMockData.ROOMS
import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENT_1
import com.spoonofcode.dojopro.core.data.mockdata.TypeMockData.TYPES
import com.spoonofcode.dojopro.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.dojopro.core.data.repository.ClubRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEventRequest
import com.spoonofcode.dojopro.feature.sportevent.di.sportEventTestModule
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SportEventEditViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: SportEventEditViewModel
    private lateinit var clubRepository: ClubRepository
    private lateinit var sportEventRepository: SportEventRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(sportEventTestModule)
        super.setup()
        clubRepository = getKoin().get()
        sportEventRepository = getKoin().get()
    }

    @Test
    fun `init view in CREATE mode`() = runTest {
        viewModel = getSut()
        viewModel.initView(ScreenMode.Create)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SportEventEditViewState(
                    isLoadingView = false,
                    screenMode = ScreenMode.Create,
                    clubs = CLUBS.associate { it.id to it.name },
                    rooms = ROOMS.associate { it.id to it.name },
                    levels = LEVELS.associate { it.id to it.name },
                    types = TYPES.associate { it.id to it.name },
                    selectedClubId = CLUBS.first().id,
                    selectedRoomId = ROOMS.first().id,
                    selectedLevelId = LEVELS.first().id,
                    selectedTypeId = TYPES.first().id,
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `init view in EDIT mode`() = runTest {
        viewModel = getSut()
        viewModel.initView(ScreenMode.Edit(SPORT_EVENT_1.id))
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SportEventEditViewState(
                    isLoadingView = false,
                    screenMode = ScreenMode.Edit(SPORT_EVENT_1.id),
                    clubs = CLUBS.associate { it.id to it.name },
                    rooms = ROOMS.associate { it.id to it.name },
                    levels = LEVELS.associate { it.id to it.name },
                    types = TYPES.associate { it.id to it.name },
                    selectedClubId = SPORT_EVENT_1.club.id,
                    selectedRoomId = SPORT_EVENT_1.room.id,
                    selectedLevelId = SPORT_EVENT_1.level.id,
                    selectedTypeId = SPORT_EVENT_1.type.id,
                    title = SPORT_EVENT_1.title,
                    description = SPORT_EVENT_1.description,
                    selectedMinNumberOfPeople = SPORT_EVENT_1.minNumberOfPeople,
                    selectedMaxNumberOfPeople = SPORT_EVENT_1.maxNumberOfPeople,
                    cost = SPORT_EVENT_1.cost,
                    startDateTime = SPORT_EVENT_1.startDateTime,
                    endDateTime = SPORT_EVENT_1.endDateTime
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `init view error`() = runTest {
        everySuspend { clubRepository.readAll() } throws Exception("load failed")

        viewModel = getSut()
        viewModel.initView(ScreenMode.Create)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SportEventEditViewState(isLoadingView = false, isErrorView = true),
                awaitItem(),
            )
        }
    }

    @Test
    fun `change title updates state`() = runTest {
        viewModel = getSut()
        viewModel.initView(ScreenMode.Create)
        advanceUntilIdle()

        val newTitle = "Brand-new title"
        viewModel.changeTitle(newTitle)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(newTitle, awaitItem().title)
        }
    }

    @Test
    fun `submit sport event in CREATE mode success`() = runTest {
        viewModel = getSut()
        viewModel.initView(ScreenMode.Create)
        advanceUntilIdle()

        val newTitle = "Brand-new title"
        val newDescription = "Brand-new description"
        val newClubId = 3
        val newRoomId = 3
        val newLevelId = 3
        val newTypeId = 3
        val newMinNumberOfPeople = 3
        val newMaxNumberOfPeople = 10
        val newCost = 100
        val newStartDateTime = LocalDateTime(2025, 5, 1, 8, 0, 0)
        val newEndDateTime = LocalDateTime(2025, 5, 1, 10, 0, 0)

        viewModel.changeTitle(newTitle)
        viewModel.changeDescription(newDescription)
        viewModel.changeClub(newClubId)
        viewModel.changeRoom(newRoomId)
        viewModel.changeLevel(newLevelId)
        viewModel.changeType(newTypeId)
        viewModel.changeMinNumberOfPeople(newMinNumberOfPeople)
        viewModel.changeMaxNumberOfPeople(newMaxNumberOfPeople)
        viewModel.changeMaxNumberOfPeople(newMaxNumberOfPeople)
        viewModel.changeCost(newCost)
        viewModel.changeStartDateTime(newStartDateTime)
        viewModel.changeEndDateTime(newEndDateTime)

        viewModel.submitSportEvent()
        advanceUntilIdle()

        verifySuspend {
            sportEventRepository.create(
                request = SportEventRequest(
                    title = newTitle,
                    description = newDescription,
                    minNumberOfPeople = newMinNumberOfPeople,
                    maxNumberOfPeople = newMaxNumberOfPeople,
                    cost = newCost,
                    startDateTime = newStartDateTime,
                    endDateTime = newEndDateTime,
                    clubId = newClubId,
                    roomId = newRoomId,
                    levelId = newLevelId,
                    typeId = newTypeId,
                    creatorUserId = USER_1.id
                )
            )
        }
        verifySuspend { viewModelNavigator.pop() }
    }

    @Test
    fun `submit sport event in EDIT mode success`() = runTest {
        viewModel = getSut()
        viewModel.initView(ScreenMode.Edit(sportEventId = SPORT_EVENT_1.id))
        advanceUntilIdle()

        val newTitle = "Brand-new title"
        val newDescription = "Brand-new description"
        val newClubId = 3
        val newRoomId = 3
        val newLevelId = 3
        val newTypeId = 3
        val newMinNumberOfPeople = 3
        val newMaxNumberOfPeople = 10
        val newCost = 100
        val newStartDateTime = LocalDateTime(2025, 5, 1, 8, 0, 0)
        val newEndDateTime = LocalDateTime(2025, 5, 1, 10, 0, 0)

        viewModel.changeTitle(newTitle)
        viewModel.changeDescription(newDescription)
        viewModel.changeClub(newClubId)
        viewModel.changeRoom(newRoomId)
        viewModel.changeLevel(newLevelId)
        viewModel.changeType(newTypeId)
        viewModel.changeMinNumberOfPeople(newMinNumberOfPeople)
        viewModel.changeMaxNumberOfPeople(newMaxNumberOfPeople)
        viewModel.changeMaxNumberOfPeople(newMaxNumberOfPeople)
        viewModel.changeCost(newCost)
        viewModel.changeStartDateTime(newStartDateTime)
        viewModel.changeEndDateTime(newEndDateTime)

        viewModel.submitSportEvent()
        advanceUntilIdle()

        verifySuspend {
            sportEventRepository.update(
                id = SPORT_EVENT_1.id,
                request = SportEventRequest(
                    title = newTitle,
                    description = newDescription,
                    minNumberOfPeople = newMinNumberOfPeople,
                    maxNumberOfPeople = newMaxNumberOfPeople,
                    cost = newCost,
                    startDateTime = newStartDateTime,
                    endDateTime = newEndDateTime,
                    clubId = newClubId,
                    roomId = newRoomId,
                    levelId = newLevelId,
                    typeId = newTypeId,
                    creatorUserId = USER_1.id
                )
            )
        }
        verifySuspend { viewModelNavigator.popToRoot() }
    }

    @Test
    fun `submit sport event in CREATE mode error hides loader`() = runTest {
        val exceptionMessage = "create failed"
        everySuspend { sportEventRepository.create(any()) } throws Exception(exceptionMessage)

        viewModel = getSut()
        viewModel.initView(ScreenMode.Create)
        advanceUntilIdle()

        viewModel.submitSportEvent()

        viewModel.snackbarEvent.test {
            assertEquals("ERROR: java.lang.Exception: $exceptionMessage", awaitItem().message)
            cancelAndIgnoreRemainingEvents()
        }

        advanceUntilIdle()
        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }

    @Test
    fun `submit sport event in EDIT mode error hides loader`() = runTest {
        val exceptionMessage = "update failed"
        everySuspend {
            sportEventRepository.update(
                any(),
                any()
            )
        } throws Exception(exceptionMessage)

        viewModel = getSut()
        viewModel.initView(ScreenMode.Edit(sportEventId = SPORT_EVENT_1.id))

        advanceUntilIdle()

        viewModel.submitSportEvent()

        viewModel.snackbarEvent.test {
            assertEquals("ERROR: java.lang.Exception: $exceptionMessage", awaitItem().message)
            cancelAndIgnoreRemainingEvents()
        }

        advanceUntilIdle()
        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }
}