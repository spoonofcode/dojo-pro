package com.spoonofcode.dojopro.feature.profile

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetProfileUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress

internal class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase
) : BaseViewModel<ProfileViewState>(ProfileViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val profile = getProfileUseCase()
            updateState {
                copy(
                    profile = profile
                )
            }
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }
}