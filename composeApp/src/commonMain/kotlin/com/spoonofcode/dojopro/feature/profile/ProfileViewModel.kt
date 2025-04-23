package com.spoonofcode.dojopro.feature.profile

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetProfileUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.feature.settings.SettingsScreen
import kotlinx.coroutines.launch

internal class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel<ProfileViewState>(ProfileViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            val profile = getProfileUseCase()
            updateState {
                copy(
                    profile = profile,
                    isLoadingView = false,
                )
            }
        }
    }

    fun navigateToSettings() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = SettingsScreen())
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(isLoadingView = true)
        }
    }
}