package com.spoonofcode.dojopro.feature.profile

import app.cash.turbine.test
import com.spoonofcode.dojopro.feature.profile.di.profileTestModule
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProfileViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: ProfileViewModel

    @BeforeTest
    override fun setup() {
        modules = arrayOf(
            profileTestModule,
        )
        super.setup()
    }

    @Test
    fun testInitial() = runTest {
        viewModel = getSut()

        viewModel.viewState.test {
            assertEquals(ProfileViewState(), awaitItem())
        }
    }
}