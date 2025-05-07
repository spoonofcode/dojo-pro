package com.spoonofcode.dojopro.feature.appsettings

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.HasSpecialSettingsUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.feature.user.search.SearchUserScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class AppSettingsViewModel(
    private val hasSpecialSettingsUseCase: HasSpecialSettingsUseCase,
) : BaseViewModel<AppSettingsViewState>(AppSettingsViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val hasSpecialSettings = hasSpecialSettingsUseCase()
                updateState {
                    copy(
                        isLoadingView = false,
                        isSearchUserButtonVisible = hasSpecialSettings,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun navigateToSearchUser() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = SearchUserScreen())
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