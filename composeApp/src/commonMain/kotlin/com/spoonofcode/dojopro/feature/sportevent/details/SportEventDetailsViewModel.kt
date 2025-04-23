package com.spoonofcode.dojopro.feature.sportevent.details

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.AddUserToSportEventUseCase
import com.spoonofcode.dojopro.core.domain.DeleteSportEventUseCase
import com.spoonofcode.dojopro.core.domain.GetSportEventByIdUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import com.spoonofcode.dojopro.feature.sportevent.edit.ScreenMode
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditScreen
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

internal class SportEventDetailsViewModel(
    private val getSportEventByIdUseCase: GetSportEventByIdUseCase,
    private val deleteSportEventUseCase: DeleteSportEventUseCase,
    private val addUserToSportEventUseCase: AddUserToSportEventUseCase,
) : BaseViewModel<SportEventDetailsViewState>(SportEventDetailsViewState()) {

    fun initView(sportEventId: Int) {
        viewModelScope.launch {
            showLoadingView()
            try {
                val sportEvent = getSportEventByIdUseCase(sportEventId = sportEventId)
                updateState {
                    copy(
                        isLoadingView = false,
                        sportEvent = sportEvent
                    )
                }
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorView()
            }
        }
    }

    fun editSportEvent() {
        viewModelScope.launch {
            showLoadingView()
            viewModelNavigator.push(SportEventEditScreen(screenMode = ScreenMode.Edit(sportEventId = currentState().sportEvent!!.id)))
        }
    }

    fun deleteSportEvent() {
        viewModelScope.launch {
            showLoadingView()
            runCatching {
                val currentState = currentState()
                deleteSportEventUseCase(
                    sportEventId = currentState.sportEvent!!.id,
                )
            }.onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
        }
    }

    fun joinToSportEvent() {
        viewModelScope.launch {
            showLoadingView()
            runCatching {
                val currentState = currentState()
                addUserToSportEventUseCase(
                    sportEventId = currentState.sportEvent!!.id,
                )
            }.onSuccess {
                viewModelNavigator.pop()
            }.onFailure {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
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