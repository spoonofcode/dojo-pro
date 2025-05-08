package com.spoonofcode.dojopro.feature.profile

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.data.repository.ProfileRepositoryInterface
import com.spoonofcode.dojopro.feature.profile.di.profileTestModule
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import org.koin.dsl.module
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProfileViewModelTest : BaseViewModelTest() {

    private val profileRepositoryMock = mock<ProfileRepositoryInterface>()
//        .apply {
//            runBlocking {
//                everySuspend { create(any<ProfileRequest>()) } returns Profile(
//                    name = "dsa",
//                    numberOfEventsUserParticipatedIn = 1,
//                    numberOfEventsCreatedByUser = 1
//                )
//            }
//        }
//        .apply {
//            runBlocking {
//                everySuspend { read(any()) } returns Profile(
//                    name = "dsa",
//                    numberOfEventsUserParticipatedIn = 1,
//                    numberOfEventsCreatedByUser = 1
//                )
//            }
//        }
        .apply { every { testBartek() } returns "xDSA BARTEK" }

    private lateinit var viewModel: ProfileViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(
            profileTestModule,
            module {
                single {
                    profileRepositoryMock
                }
            }
        )
        super.setup()
    }

    @Test
    fun testInitial() = runTest {
        viewModel = getSut()

        viewModel.testBartek()

        viewModel.viewState.test {
            assertEquals(ProfileViewState(), awaitItem())
        }
    }
}