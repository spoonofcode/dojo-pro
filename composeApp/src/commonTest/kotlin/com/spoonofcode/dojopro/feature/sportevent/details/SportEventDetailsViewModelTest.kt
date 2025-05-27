package com.spoonofcode.dojopro.feature.sportevent.details

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENT_1
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.feature.sportevent.di.sportEventTestModule
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditScreen
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SportEventDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: SportEventDetailsViewModel
    private lateinit var sportEventRepository: SportEventRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(sportEventTestModule)     // Koin test module with mocks
        super.setup()
        sportEventRepository = getKoin().get()
    }

    // ───────────────────────── initView ─────────────────────────
    @Test
    fun `init view success`() = runTest {
        viewModel = getSut()
        viewModel.initView(SPORT_EVENT_1.id)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SportEventDetailsViewState(
                    isLoadingView = false,
                    sportEvent = SPORT_EVENT_1,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `init view error`() = runTest {
        everySuspend { sportEventRepository.read(any()) } throws Exception("not found")

        viewModel = getSut()
        viewModel.initView(SPORT_EVENT_1.id)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SportEventDetailsViewState(
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `edit sport event navigates to edit screen`() = runTest {
        viewModel = getSut()
        viewModel.initView(SPORT_EVENT_1.id)
        advanceUntilIdle()

        viewModel.editSportEvent()
        advanceUntilIdle()

        verifySuspend {
            viewModelNavigator.push(
                ofType<SportEventEditScreen>()
            )
        }
    }

    @Test
    fun `delete sport event success`() = runTest {
        viewModel = getSut()
        viewModel.initView(SPORT_EVENT_1.id)
        advanceUntilIdle()

        viewModel.deleteSportEvent()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.pop() }
    }

    @Test
    fun `delete sport event error`() = runTest {
        everySuspend { sportEventRepository.delete(any()) } throws Exception("not found")

        viewModel = getSut()
        viewModel.initView(SPORT_EVENT_1.id)
        advanceUntilIdle()

        viewModel.deleteSportEvent()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }

    @Test
    fun `join to sport event success`() = runTest {
        viewModel = getSut()
        viewModel.initView(SPORT_EVENT_1.id)
        advanceUntilIdle()

        viewModel.joinToSportEvent()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.pop() }
    }

    @Test
    fun `join to sport event error`() = runTest {
        everySuspend {
            sportEventRepository.addUserToSportEvent(
                any(),
                any()
            )
        } throws Exception("not found")

        viewModel = getSut()
        viewModel.initView(SPORT_EVENT_1.id)
        advanceUntilIdle()

        viewModel.joinToSportEvent()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(false, awaitItem().isLoadingView)
        }
    }
}