package com.spoonofcode.dojopro.feature.user.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.AddRoleToUserUseCase
import com.spoonofcode.dojopro.core.domain.GetRolesByUserIdUseCase
import com.spoonofcode.dojopro.core.domain.GetUserByIdUseCase
import com.spoonofcode.dojopro.core.model.Roles
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import kotlinx.coroutines.launch

internal class UserDetailsViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val addRoleToUserUseCase: AddRoleToUserUseCase,
    private val getRolesByUserIdUseCase: GetRolesByUserIdUseCase,
) : BaseViewModel<UserDetailsViewState>(UserDetailsViewState()) {

    fun initView(userId: Int) {
        viewModelScope.launch {
            val user = getUserByIdUseCase(userId = userId)
            val roles = getRolesByUserIdUseCase(userId = userId)
            updateState {
                copy(
                    user = user,
                    roles = roles.joinToString(separator = ",") { it.name },
                    isVisibleAddCoachRoleButton = roles.none { it.id == Roles.COACH.id },
                    isVisibleAddClubOwnerRoleButton = roles.none { it.id == Roles.CLUB_OWNER.id },
                )
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
        val userId = currentState().user!!.id
        runCatching {
            addRoleToUserUseCase(roleId = roleId, userId = userId)
        }.onSuccess {
            val roles = getRolesByUserIdUseCase(userId = userId)
            updateState {
                copy(
                    roles = roles.joinToString(separator = ",") { it.name },
                    isVisibleAddCoachRoleButton = roles.none { it.id == Roles.COACH.id },
                    isVisibleAddClubOwnerRoleButton = roles.none { it.id == Roles.CLUB_OWNER.id },
                )
            }
        }.onFailure {
            showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(isLoadingView = true)
        }
    }
}