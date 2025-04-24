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
            viewModelNavigator.push(SportEventEditScreen(screenMode = ScreenMode.Edit(sportEventId = currentState().sportEvent!!.id)))
        }
    }

    fun deleteSportEvent() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
                deleteSportEventUseCase(
                    sportEventId = currentState.sportEvent!!.id,
                )
                viewModelNavigator.pop()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
            }
        }
    }

    fun joinToSportEvent() {
        viewModelScope.launch {
            showLoadingView()
            try {
                val currentState = currentState()
                addUserToSportEventUseCase(
                    sportEventId = currentState.sportEvent!!.id,
                )
                viewModelNavigator.pop()
            } catch (ce: CancellationException) {
                throw ce
            } catch (e: Exception) {
                showErrorSnackbar(e)
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

    private fun showErrorSnackbar(e: Exception) {
        showSnackbar(SnackbarEvent.Error(message = "ERROR: $e"))
        updateState {
            copy(
                isLoadingView = false
            )
        }
    }
}