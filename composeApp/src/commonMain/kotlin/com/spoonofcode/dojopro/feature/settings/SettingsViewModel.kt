package com.spoonofcode.dojopro.feature.settings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.user.search.SearchUserScreen
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    private val hasSpecialSettingsUseCase: HasSpecialSettingsUseCase,
) : BaseViewModel<SettingsViewState>(SettingsViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val hasSpecialSettings = hasSpecialSettingsUseCase()
            updateState {
                copy(
                    isSearchUserButtonVisible = hasSpecialSettings,
                )
            }
        }
    }

    fun navigateToSearchUser() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = SearchUserScreen())
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }
}