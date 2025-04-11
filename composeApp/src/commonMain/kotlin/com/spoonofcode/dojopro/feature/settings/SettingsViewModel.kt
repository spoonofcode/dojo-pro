package com.spoonofcode.dojopro.feature.settings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetUserRolesUseCase
import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.model.Roles
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.user.search.SearchUserScreen
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    private val getUserRolesUseCase: GetUserRolesUseCase
) : BaseViewModel<SettingsViewState>(SettingsViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val roles = getUserRolesUseCase()
            setSpecialSettings(roles)
        }
    }

    fun navigateToSearchUser() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = SearchUserScreen())
        }
    }

    private fun setSpecialSettings(roles: List<Role>) {
        val canSaerchUser =
            roles.any { it.id == Roles.ADMIN.id || it.id == Roles.CLUB_OWNER.id }
        updateState {
            copy(
                isSearchUserButtonVisible = canSaerchUser,
            )
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }
}