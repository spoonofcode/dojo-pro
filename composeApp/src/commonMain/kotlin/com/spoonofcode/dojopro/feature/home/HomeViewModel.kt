package com.spoonofcode.dojopro.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetSportEventsCreatedByUserUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsUserParticipatedInUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditScreen
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val getSportEventsUserParticipatedInUseCase: GetSportEventsUserParticipatedInUseCase,
    private val getSportEventsCreatedByUserUseCase: GetSportEventsCreatedByUserUseCase,
) : BaseViewModel<HomeViewState>(HomeViewState()) {

    init {
        initView()
    }

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

    fun goToCreateSportEvent() {
        viewModelScope.launch {
            viewModelNavigator.push(SportEventEditScreen())
        }
    }

    fun goToMyEvent(sportEventId: Int) {
        viewModelScope.launch {
            viewModelNavigator.push(SportEventDetailsScreen(sportEventId))
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }
}