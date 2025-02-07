package com.spoonofcode.dojopro.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.base.ui.BaseViewModel
import com.spoonofcode.dojopro.core.base.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository

internal class HomeViewModel(
    private val sportEventRepository: SportEventRepository,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val sportEvents = sportEventRepository.readAll()
            updateState {
                copy(
                    sportEvents = sportEvents
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