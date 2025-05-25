package com.spoonofcode.dojopro.feature.home

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENTS
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.feature.home.di.homeTestModule
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditScreen
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.matcher.eq
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var sportEventRepository: SportEventRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(homeTestModule)
        super.setup()
        sportEventRepository = getKoin().get()
    }

    @Test
    fun `init view`() = runTest {
        // when
        viewModel = getSut()
        viewModel.initView()      // even though HomeViewModel calls this in its init block,
        advanceUntilIdle()        // we repeat it to keep the pattern identical to ProfileViewModelTest

        viewModel.viewState.test {
            assertEquals(
                HomeViewState(
                    sportEventsUserParticipatedIn = SPORT_EVENTS,
                    sportEventsCreatedByUser = SPORT_EVENTS,
                    isLoadingView = false
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `init view with error`() = runTest {
        everySuspend { sportEventRepository.readSportEventsCreatedByUser(any()) } throws Exception("test exception")

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                HomeViewState(
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `go to create sport event`() = runTest {
        viewModel = getSut()

        viewModel.goToCreateSportEvent()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<SportEventEditScreen>()) }
    }

    @Test
    fun `go to my event`() = runTest {
        viewModel = getSut()
        val eventId = 42

        viewModel.goToMyEvent(eventId)
        advanceUntilIdle()

        // Verify the navigator was asked to push a details screen carrying the right id
        verifySuspend {
            viewModelNavigator.push(
                eq(SportEventDetailsScreen(sportEventId = eventId))
            )
        }
    }
}