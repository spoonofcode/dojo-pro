package com.spoonofcode.dojopro.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetSportEventsCreatedByUserUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsUserParticipatedInUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
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
        viewModelScope.launch {
            showLoadingView()
            val sportEventsUserParticipatedIn = getSportEventsUserParticipatedInUseCase()
            val sportEventsCreatedByUser = getSportEventsCreatedByUserUseCase()
            updateState {
                copy(
                    sportEventsUserParticipatedIn = sportEventsUserParticipatedIn,
                    sportEventsCreatedByUser = sportEventsCreatedByUser,
                    isLoadingView = false,
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
            viewModelNavigator.push(
                SportEventDetailsScreen(
                    sportEventId = sportEventId
                )
            )
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(isLoadingView = true)
        }
    }
}