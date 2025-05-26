package com.spoonofcode.dojopro.feature.search.search

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENTS
import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENT_1
import com.spoonofcode.dojopro.core.data.repository.FilterData
import com.spoonofcode.dojopro.core.data.repository.FilterRepository
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.feature.search.di.searchTestModule
import com.spoonofcode.dojopro.feature.search.filter.FilterScreen
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.matcher.eq
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var filterRepository: FilterRepository
    private lateinit var sportEventRepository: SportEventRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(searchTestModule)            // Koin test module with mocks
        super.setup()
        filterRepository = getKoin().get()
        sportEventRepository = getKoin().get()
    }

    @Test
    fun `init view success`() = runTest {
        every { filterRepository.getSelectedFilters() } returns FilterData(
            startDateTime = LocalDateTime(2022, 1, 1, 1, 1),
            endDateTime = LocalDateTime(2025, 1, 1, 1, 1),
        )

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SearchViewState(
                    isLoadingView = false,
                    initSportEvents = SPORT_EVENTS,
                    filteredSportEvents = SPORT_EVENTS,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `init view error`() = runTest {
        everySuspend { sportEventRepository.readAll() } throws Exception("load failed")

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SearchViewState(
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `change search text filters list and updates state`() = runTest {
        every { filterRepository.getSelectedFilters() } returns FilterData(
            startDateTime = LocalDateTime(2022, 1, 1, 1, 1),
            endDateTime = LocalDateTime(2025, 1, 1, 1, 1),
        )

        val query = SPORT_EVENT_1.title

        viewModel = getSut()
        advanceUntilIdle()

        viewModel.changeSearchText(query)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                listOf(SPORT_EVENT_1),
                awaitItem().filteredSportEvents,
            )
        }
    }

    @Test
    fun `select sport event navigates to details`() = runTest {
        viewModel = getSut()
        val id = 42

        viewModel.selectSportEvent(id)
        advanceUntilIdle()

        verifySuspend {
            viewModelNavigator.push(eq(SportEventDetailsScreen(sportEventId = id)))
        }
    }

    @Test
    fun `navigate to filter`() = runTest {
        viewModel = getSut()

        viewModel.navigateToFilter()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<FilterScreen>()) }
    }
}