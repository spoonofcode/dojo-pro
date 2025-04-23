package com.spoonofcode.dojopro.feature.user.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.AddRoleToUserUseCase
import com.spoonofcode.dojopro.core.domain.GetRolesByUserIdUseCase
import com.spoonofcode.dojopro.core.domain.GetUserByIdUseCase
import com.spoonofcode.dojopro.core.model.Roles
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class UserDetailsViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val addRoleToUserUseCase: AddRoleToUserUseCase,
    private val getRolesByUserIdUseCase: GetRolesByUserIdUseCase,
) : BaseViewModel<UserDetailsViewState>(UserDetailsViewState()) {

    fun initView(userId: Int) {
        viewModelScope.launch {
            try {
                val user = getUserByIdUseCase(userId = userId)
                val roles = getRolesByUserIdUseCase(userId = userId)
                updateState {
                    copy(
                        isLoadingView = false,
                        user = user,
                        roles = roles.joinToString(separator = ",") { it.name },
                        isVisibleAddCoachRoleButton = roles.none { it.id == Roles.COACH.id },
                        isVisibleAddClubOwnerRoleButton = roles.none { it.id == Roles.CLUB_OWNER.id },
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun addCoachRole() {
        viewModelScope.launch {
            addRoleToUser(roleId = Roles.COACH.id)
        }
    }

    fun addClubOwnerRole() {
        viewModelScope.launch {
            addRoleToUser(roleId = Roles.CLUB_OWNER.id)
        }
    }

    private suspend fun addRoleToUser(roleId: Int) {
        showLoadingView()
        try {
            val userId = currentState().user!!.id
            addRoleToUserUseCase(roleId = roleId, userId = userId)
            val roles = getRolesByUserIdUseCase(userId = userId)
            updateState {
                copy(
                    isLoadingView = false,
                    roles = roles.joinToString(separator = ",") { it.name },
                    isVisibleAddCoachRoleButton = roles.none { it.id == Roles.COACH.id },
                    isVisibleAddClubOwnerRoleButton = roles.none { it.id == Roles.CLUB_OWNER.id },
                )
            }
        } catch (ce: CancellationException) {
            throw ce
        } catch (e: Exception) {
            showErrorView()
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}