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
            val sportEvents = homeUseCase.getAllSportEvents()
            val sportEventsIParticipatedIn = homeUseCase.getSportEventsIParticipatedIn()
            updateState {
                copy(
                    allSportEvents = sportEvents,
                    sportEventsIParticipatedIn = sportEventsIParticipatedIn,
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