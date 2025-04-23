package com.spoonofcode.dojopro.feature.home

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetSportEventsCreatedByUserUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventsUserParticipatedInUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

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
            try {
                val sportEventsUserParticipatedIn = getSportEventsUserParticipatedInUseCase()
                val sportEventsCreatedByUser = getSportEventsCreatedByUserUseCase()
                updateState {
                    copy(
                        sportEventsUserParticipatedIn = sportEventsUserParticipatedIn,
                        sportEventsCreatedByUser = sportEventsCreatedByUser,
                        isLoadingView = false,
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
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