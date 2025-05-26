package com.spoonofcode.dojopro.feature.search.filter

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.ClubMockData.CLUBS
import com.spoonofcode.dojopro.core.data.mockdata.ClubMockData.CLUB_1
import com.spoonofcode.dojopro.core.data.mockdata.LevelMockData.LEVELS
import com.spoonofcode.dojopro.core.data.mockdata.LevelMockData.LEVEL_1
import com.spoonofcode.dojopro.core.data.mockdata.TypeMockData.TYPES
import com.spoonofcode.dojopro.core.data.mockdata.TypeMockData.TYPE_1
import com.spoonofcode.dojopro.core.data.mockdata.UserMockData.USERS
import com.spoonofcode.dojopro.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.dojopro.core.data.repository.ClubRepository
import com.spoonofcode.dojopro.core.data.repository.FilterData
import com.spoonofcode.dojopro.core.data.repository.FilterRepository
import com.spoonofcode.dojopro.feature.search.di.searchTestModule
import com.spoonofcode.dojopro.feature.search.filter.FilterViewState.Companion.ALL_OPTION_ID
import com.spoonofcode.dojopro.feature.search.filter.FilterViewState.Companion.ALL_OPTION_NAME
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.every
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
class FilterViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: FilterViewModel

    private lateinit var clubRepository: ClubRepository
    private lateinit var filterRepository: FilterRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(searchTestModule)
        super.setup()
        clubRepository = getKoin().get()
        filterRepository = getKoin().get()
    }

    @Test
    fun `init view with NO previous selections`() = runTest {
        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        val expected = FilterViewState(
            isLoadingView = false,
            selectedClubId = ALL_OPTION_ID,
            selectedCoachId = ALL_OPTION_ID,
            selectedLevelId = ALL_OPTION_ID,
            selectedTypeId = ALL_OPTION_ID,
            clubs = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(CLUBS.associate { it.id to it.name }),
            coaches = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(USERS.associate { it.id to it.fullName }),
            levels = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(LEVELS.associate { it.id to it.name }),
            types = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(TYPES.associate { it.id to it.name }),
        )

        viewModel.viewState.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `init view with PREVIOUS selections`() = runTest {
        val startDateTime = LocalDateTime(2025, 5, 1, 8, 0, 0)
        val endDateTime = LocalDateTime(2025, 5, 1, 10, 0, 0)

        every { filterRepository.getSelectedFilters() } returns FilterData(
            selectedClubId = CLUB_1.id,
            selectedCoachId = USER_1.id,
            selectedLevelId = LEVEL_1.id,
            selectedTypeId = TYPE_1.id,
            startDateTime = startDateTime,
            endDateTime = endDateTime,
        )

        viewModel = getSut()
        advanceUntilIdle()

        viewModel.initView()
        advanceUntilIdle()

        val expected = FilterViewState(
            isLoadingView = false,
            selectedClubId = CLUB_1.id,
            selectedCoachId = USER_1.id,
            selectedLevelId = LEVEL_1.id,
            selectedTypeId = TYPE_1.id,
            clubs = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(CLUBS.associate { it.id to it.name }),
            coaches = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(USERS.associate { it.id to it.fullName }),
            levels = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(LEVELS.associate { it.id to it.name }),
            types = mapOf(ALL_OPTION_ID to ALL_OPTION_NAME).plus(TYPES.associate { it.id to it.name }),
            startDateTime = startDateTime,
            endDateTime = endDateTime,
        )

        viewModel.viewState.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `init view with ERROR`() = runTest {
        everySuspend { clubRepository.readAll() } throws Exception("test exception")

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        val expected = FilterViewState(
            isLoadingView = false,
            isErrorView = true,
        )

        viewModel.viewState.test {
            assertEquals(expected, awaitItem())
        }
    }

    @Test
    fun `change club updates state`() = runTest {
        viewModel = getSut()
        advanceUntilIdle()

        val newClubId = CLUB_1.id
        viewModel.changeClub(newClubId)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(newClubId, awaitItem().selectedClubId)
        }
    }

    @Test
    fun `apply filter SUCCESS`() = runTest {
        viewModel = getSut()
        advanceUntilIdle()

        viewModel.changeClub(CLUB_1.id)
        viewModel.changeCoach(USER_1.id)
        viewModel.changeLevel(LEVEL_1.id)
        viewModel.changeType(TYPE_1.id)
        val startDateTime = LocalDateTime(2025, 5, 1, 8, 0, 0)
        val endDateTime = LocalDateTime(2025, 5, 1, 10, 0, 0)
        viewModel.changeStartDateTime(startDateTime)
        viewModel.changeEndDateTime(endDateTime)
        advanceUntilIdle()

        viewModel.applyFilter()
        advanceUntilIdle()

        verifySuspend {
            filterRepository.setSelectedFilters(
                newFilterData = FilterData(
                    selectedClubId = CLUB_1.id,
                    selectedCoachId = USER_1.id,
                    selectedLevelId = LEVEL_1.id,
                    selectedTypeId = TYPE_1.id,
                    startDateTime = startDateTime,
                    endDateTime = endDateTime,
                )
            )
        }
        verifySuspend { viewModelNavigator.pop() }
    }

    @Test
    fun `apply filter ERROR`() = runTest {
        every { filterRepository.setSelectedFilters(any()) } throws Exception("test exception")

        viewModel = getSut()
        advanceUntilIdle()

        viewModel.applyFilter()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }
}