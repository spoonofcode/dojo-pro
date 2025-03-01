package com.spoonofcode.dojopro.feature.sportevent.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.AddUserToSportEventUseCase
import com.spoonofcode.dojopro.core.domain.DeleteSportEventUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventByIdUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.sportevent.edit.ScreenMode
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditScreen

internal class SportEventDetailsViewModel(
    private val getSportEventByIdUseCase: GetSportEventByIdUseCase,
    private val deleteSportEventUseCase: DeleteSportEventUseCase,
    private val addUserToSportEventUseCase: AddUserToSportEventUseCase,
) : BaseViewModel<SportEventDetailsViewState>(SportEventDetailsViewState()) {

    fun initView(sportEventId: Int) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val sportEvent = getSportEventByIdUseCase(sportEventId = sportEventId)
            updateState {
                copy(
                    sportEvent = sportEvent
                )
            }
        }
    }

    fun editSportEvent() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            viewModelNavigator.push(SportEventEditScreen(screenMode = ScreenMode.Edit(sportEventId = currentState().sportEvent!!.id)))
        }
    }

    fun deleteSportEvent() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                val currentState = currentState()
                deleteSportEventUseCase(
                    sportEventId = currentState.sportEvent!!.id,
                )
            }.onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showSnackbar("ERROR: $it")
            }
        }
    }

    fun joinToSportEvent() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                val currentState = currentState()
                addUserToSportEventUseCase(
                    sportEventId = currentState.sportEvent!!.id,
                )
            }.onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showSnackbar("ERROR: $it")
            }
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }
}