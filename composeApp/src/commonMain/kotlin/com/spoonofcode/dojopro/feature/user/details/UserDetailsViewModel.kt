package com.spoonofcode.dojopro.feature.user.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.AddRoleToUserUseCase
import com.spoonofcode.dojopro.core.domain.GetRolesByUserIdUseCase
import com.spoonofcode.dojopro.core.domain.GetUserByIdUseCase
import com.spoonofcode.dojopro.core.model.Roles
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress

internal class UserDetailsViewModel(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val addRoleToUserUseCase: AddRoleToUserUseCase,
    private val getRolesByUserIdUseCase: GetRolesByUserIdUseCase,
) : BaseViewModel<UserDetailsViewState>(UserDetailsViewState()) {

    fun initView(userId: Int) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val user = getUserByIdUseCase(userId = userId)
            val roles = getRolesByUserIdUseCase(userId = userId)
            updateState {
                copy(
                    user = user,
                    roles = roles,
                    isVisibleAddCoachRoleButton = roles.none { it.id == Roles.COACH.id },
                    isVisibleAddClubOwnerRoleButton = roles.none { it.id == Roles.CLUB_OWNER.id },
                )
            }
        }
    }

    fun addCoachRole() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val userId = currentState().user!!.id
            addRoleToUserUseCase(roleId = Roles.COACH.id, userId = userId)
            val roles = getRolesByUserIdUseCase(userId = userId)
            updateState {
                copy(
                    roles = roles,
                    isVisibleAddCoachRoleButton = roles.none { it.id == Roles.COACH.id },
                    isVisibleAddClubOwnerRoleButton = roles.none { it.id == Roles.CLUB_OWNER.id },
                )
            }
        }
    }

    fun addClubOwnerRole() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val userId = currentState().user!!.id
            addRoleToUserUseCase(roleId = Roles.CLUB_OWNER.id, userId = userId)
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }
}