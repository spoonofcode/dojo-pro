package com.spoonofcode.dojopro.feature.profile

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.repository.ProfileRepository
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.feature.profile.di.profileTestModule
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
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

//    @Test
//    fun testInitial() = runTest {
//        viewModel = getSut()
//
//        viewModel.testBartek()
//
//        viewModel.viewState.test {
//            assertEquals(ProfileViewState(), awaitItem())
//        }
//    }

    @Test
    fun testInitial() = runTest {

        runBlocking {
            everySuspend { profileRepository.read(any()) } returns Profile(
                name = "Profile name 12",
                numberOfEventsUserParticipatedIn = 1,
                numberOfEventsCreatedByUser = 1
            )
        }

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
}