package com.spoonofcode.dojopro.feature.user.details

import app.cash.turbine.test
import com.spoonofcode.dojopro.core.BaseViewModelTest
import com.spoonofcode.dojopro.core.data.mockdata.RoleMockData.ROLES
import com.spoonofcode.dojopro.core.data.mockdata.UserMockData.USER_1
import com.spoonofcode.dojopro.core.data.repository.UserRepository
import com.spoonofcode.dojopro.feature.user.di.userTestModule
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: UserDetailsViewModel
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
        viewModel.initView(USER_1.id)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                UserDetailsViewState(
                    isLoadingView = false,
                    user = USER_1,
                    roles = ROLES.joinToString(separator = ",") { it.name },
                    isVisibleAddCoachRoleButton = false,
                    isVisibleAddClubOwnerRoleButton = false,
                ),
                awaitItem(),
            )
        }
    }

    @Test
    fun `init view error`() = runTest {
        everySuspend { userRepository.read(any()) } throws Exception("test exception")

        viewModel = getSut()
        viewModel.initView(USER_1.id)
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                UserDetailsViewState(
                    isLoadingView = false,
                    isErrorView = true,
                ),
                awaitItem(),
            )
        }
    }

//    @Test
//    fun `add coach role success updates state and hides button`() = runTest {
//        val initialRoles  = listOf(Roles.PLAYER)
//        val updatedRoles  = initialRoles + Roles.COACH
//
//        everySuspend { getUserById(user.id) } returns user
//        everySuspend { getRolesByUserId(user.id) } returnsMany listOf(initialRoles, updatedRoles)
//        everySuspend { addRoleToUser(roleId = Roles.COACH.id, userId = user.id) } returns Unit
//
//        viewModel = getSut()
//        viewModel.initView(user.id)
//        advanceUntilIdle()
//
//        viewModel.addCoachRole()
//        advanceUntilIdle()
//
//        verifySuspend { addRoleToUser(roleId = Roles.COACH.id, userId = user.id) }
//
//        viewModel.viewState.test {
//            awaitItem()                       // after first load
//            val afterAdd = awaitItem()        // after role added
//            assertFalse(afterAdd.isLoadingView)
//            assertFalse(afterAdd.isVisibleAddCoachRoleButton)
//            assertTrue(afterAdd.roles.contains(Roles.COACH.name))
//        }
//    }

    @Test
    fun `add club owner role success updates state`() = runTest {
        viewModel = getSut()
        viewModel.initView(USER_1.id)
        advanceUntilIdle()

        viewModel.addClubOwnerRole()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertEquals(
                UserDetailsViewState(
                    isLoadingView = false,
                    user = USER_1,
                    roles = ROLES.joinToString(separator = ",") { it.name },
                    isVisibleAddCoachRoleButton = false,
                    isVisibleAddClubOwnerRoleButton = false,
                ),
                awaitItem(),
            )

//            awaitItem()
//            val afterAdd = awaitItem()
//            assertFalse(afterAdd.isLoadingView)
//            assertFalse(afterAdd.isVisibleAddClubOwnerRoleButton)
//            assertTrue(afterAdd.roles.contains(Roles.CLUB_OWNER.name))
        }
    }

    @Test
    fun `add role error hides loader`() = runTest {
        everySuspend {
            userRepository.addRoleToUser(
                any(),
                any()
            )
        } throws Exception("test exception")

        viewModel = getSut()
        viewModel.initView(USER_1.id)
        advanceUntilIdle()

        viewModel.addCoachRole()
        advanceUntilIdle()

        viewModel.viewState.test {
            assertFalse(awaitItem().isLoadingView)
        }
    }
}