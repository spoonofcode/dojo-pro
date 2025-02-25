package com.spoonofcode.dojopro.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.HomeUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress

internal class HomeViewModel(
    private val homeUseCase: HomeUseCase,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val sportEventsUserParticipatedIn = homeUseCase.getSportEventsUserParticipatedIn()
            val sportEventsCreatedByUser = homeUseCase.getSportEventsCreatedByUser()
            updateState {
                copy(
                    sportEventsUserParticipatedIn = sportEventsUserParticipatedIn,
                    sportEventsCreatedByUser = sportEventsCreatedByUser,
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