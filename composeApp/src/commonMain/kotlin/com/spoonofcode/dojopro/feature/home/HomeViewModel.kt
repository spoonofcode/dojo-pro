package com.spoonofcode.dojopro.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetSportEventsCreatedByUserUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsUserParticipatedInUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress

internal class HomeViewModel(
    private val getSportEventsUserParticipatedInUseCase: GetSportEventsUserParticipatedInUseCase,
    private val getSportEventsCreatedByUserUseCase: GetSportEventsCreatedByUserUseCase,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val sportEventsUserParticipatedIn = getSportEventsUserParticipatedInUseCase()
            val sportEventsCreatedByUser = getSportEventsCreatedByUserUseCase()
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