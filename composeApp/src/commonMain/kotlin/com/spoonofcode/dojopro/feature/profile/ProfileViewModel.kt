package com.spoonofcode.dojopro.feature.profile

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetProfileUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.feature.appsettings.AppSettingsScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel<ProfileViewState>(ProfileViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val profile = getProfileUseCase()
                println("BARTEK TEST profile = $profile")

//                updateState {
//                    copy(
//                        profile = profile,
//                        isLoadingView = false,
//                    )
//                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun navigateToSettings() {
        viewModelScope.launch {
            viewModelNavigator.push(screen = AppSettingsScreen())
        }
    }

    fun testBartek() {
        updateState {
            copy(
                isErrorView = true,
            )
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