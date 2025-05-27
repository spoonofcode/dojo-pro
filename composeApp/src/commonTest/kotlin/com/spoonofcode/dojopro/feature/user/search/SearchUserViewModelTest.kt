package com.spoonofcode.dojopro.feature.user.search

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.UserMockData.USERS
import com.spoonofcode.dojopro.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.feature.user.details.UserDetailsScreen
import com.spoonofcode.dojopro.feature.user.di.userTestModule
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.ofType
import dev.mokkery.verifySuspend
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class SearchUserViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: SearchUserViewModel
    private lateinit var userRepository: UserRepository

    @BeforeTest
    override fun setup() {
        modules = arrayOf(userTestModule)
        super.setup()
        userRepository = getKoin().get()
    }

    @Test
    fun `init view success`() = runTest {
        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SearchUserViewState(
                    isLoadingView = false,
                    initUsers = USERS,
                    filteredUsers = USERS,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `init view error`() = runTest {
        everySuspend { userRepository.readAll() } throws Exception("load failed")

        viewModel = getSut()
        viewModel.initView()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SearchUserViewState(
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `change search text filters list`() = runTest {
        val query = USER_1.fullName

        viewModel = getSut()
        advanceUntilIdle()

        viewModel.changeSearchText(query)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                SearchUserViewState(
                    searchText = query,
                    filteredUsers = listOf(USER_1),
                    initUsers = USERS,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `select user navigates to details screen`() = runTest {
        viewModel = getSut()
        val id = 42

        viewModel.selectUser(id)
        advanceUntilIdle()

        verifySuspend { viewModelNavigator.push(ofType<UserDetailsScreen>()) }
    }
}