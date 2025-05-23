package com.spoonofcode.dojopro.feature.profile

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.repository.ProfileRepository
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.feature.appsettings.AppSettingsScreen
import com.spoonofcode.dojopro.feature.profile.di.profileTestModule
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var profileRepository: ProfileRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(
            profileTestModule,
        )
        super.setup()
        profileRepository = getKoin().get()
    }

    @Test
    fun `init view`() = runTest {
        viewModel = getSut()

        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                ProfileViewState(
                    profile = Profile(
                        name = "Profile name 1",
                        numberOfEventsUserParticipatedIn = 1,
                        numberOfEventsCreatedByUser = 1
                    ),
                    isLoadingView = false
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `init view with error`() = runTest {
        runBlocking {
            everySuspend { profileRepository.read(any()) } throws Exception("test exception")
        }

        viewModel = getSut()

        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                ProfileViewState(
                    profile = null,
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem()
            )
        }
    }

    @Test
    fun `navigate to settings`() = runTest {
        viewModel = getSut()

        viewModel.navigateToSettings()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<AppSettingsScreen>()) }
    }
}