package com.spoonofcode.dojopro.feature.user

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetUserRolesUseCase
import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.model.Roles
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch

internal class SearchUserViewModel(
    private val getUserRolesUseCase: GetUserRolesUseCase
) : BaseViewModel<SearchUserViewState>(SearchUserViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val roles = getUserRolesUseCase()
            setSpecialSearchUser(roles)
        }
    }

    fun navigateToUpdateUsers() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = SearchUserScreen())
        }
    }

    private fun setSpecialSearchUser(roles: List<Role>) {
        val canUpdateUsers =
            roles.any { it.id == Roles.ADMIN.id || it.id == Roles.CLUB_OWNER.id }
        updateState {
            copy(
                isUpdateUsersButtonVisible = canUpdateUsers,
            )
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }
}