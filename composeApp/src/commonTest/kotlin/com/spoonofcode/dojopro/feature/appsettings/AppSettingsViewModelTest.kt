package com.spoonofcode.dojopro.feature.appsettings

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.RoleMockData.ROLE_4
import com.spoonofcode.dojopro.core.data.repository.RoleRepository
import com.spoonofcode.dojopro.feature.appsettings.di.appSettingsTestModule
import com.spoonofcode.dojopro.feature.user.search.SearchUserScreen
import dev.mokkery.answering.returns
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
class AppSettingsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: AppSettingsViewModel
    private lateinit var roleRepository: RoleRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(appSettingsTestModule)
        super.setup()
        roleRepository = getKoin().get()
    }

    @Test
    fun `init view when user HAS special settings`() = runTest {
        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                AppSettingsViewState(
                    isLoadingView = false,
                    isSearchUserButtonVisible = true,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `init view when user does NOT have special settings`() = runTest {
        everySuspend { roleRepository.readAllRolesByUserId(any()) } returns listOf(ROLE_4)

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                AppSettingsViewState(
                    isLoadingView = false,
                    isSearchUserButtonVisible = false,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `init view with error`() = runTest {
        everySuspend { roleRepository.readAllRolesByUserId(any()) } throws Exception("test exception")

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                AppSettingsViewState(
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `navigate to search user`() = runTest {
        viewModel = getSut()

        viewModel.navigateToSearchUser()
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<SearchUserScreen>()) }
    }
}