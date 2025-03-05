package com.spoonofcode.dojopro.feature.sportevent.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.CreateSportEventUseCase
import com.spoonofcode.dojopro.core.domain.EditSportEventUseCase
import com.spoonofcode.dojopro.core.domain.LoadSportEventFormDataUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

internal class SportEventEditViewModel(
    private val createSportEventUseCase: CreateSportEventUseCase,
    private val editSportEventUseCase: EditSportEventUseCase,
    private val loadSportEventFormDataUseCase: LoadSportEventFormDataUseCase,
) : BaseViewModel<SportEventEditViewState>(SportEventEditViewState()) {

    fun initView(screenMode: ScreenMode) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                loadSportEventFormDataUseCase(screenMode = screenMode)
            }.onSuccess { sportEventFormData ->
                if (sportEventFormData.sportEvent == null) {
                    updateState {
                        copy(
                            screenMode = screenMode,
                            coaches = sportEventFormData.coaches.associate { it.id to it.fullName },
                            rooms = sportEventFormData.rooms.associate { it.id to it.name },
                            levels = sportEventFormData.levels.associate { it.id to it.name },
                            selectedCoachId = sportEventFormData.coaches.first().id,
                            selectedRoomId = sportEventFormData.rooms.first().id,
                            selectedLevelId = sportEventFormData.levels.first().id,
                        )
                    }
                } else {
                    updateState {
                        copy(
                            screenMode = screenMode,
                            coaches = sportEventFormData.coaches.associate { it.id to it.fullName },
                            rooms = sportEventFormData.rooms.associate { it.id to it.name },
                            levels = sportEventFormData.levels.associate { it.id to it.name },
                            selectedCoachId = sportEventFormData.sportEvent.coach.id,
                            selectedRoomId = sportEventFormData.sportEvent.room.id,
                            selectedLevelId = sportEventFormData.sportEvent.level.id,
                            title = sportEventFormData.sportEvent.title,
                            description = sportEventFormData.sportEvent.description,
                            selectedMinNumberOfPeople = sportEventFormData.sportEvent.minNumberOfPeople,
                            selectedMaxNumberOfPeople = sportEventFormData.sportEvent.maxNumberOfPeople,
                            cost = sportEventFormData.sportEvent.cost,
                            startDateTime = sportEventFormData.sportEvent.startDateTime,
                            endDateTime = sportEventFormData.sportEvent.endDateTime,
                        )
                    }
                }
            }
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
        }
    }

    fun changeTitle(title: String) {
        viewModelScope.launch {
            updateState {
                copy(title = title)
            }
        }
    }

    fun changeDescription(description: String) {
        viewModelScope.launch {
            updateState {
                copy(description = description)
            }
        }
    }

    fun changeCoach(selectedCoachId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedCoachId = selectedCoachId)
            }
        }
    }

    fun changeRoom(selectedRoomId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedRoomId = selectedRoomId)
            }
        }
    }

    fun changeLevel(selectedLevelId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedLevelId = selectedLevelId)
            }
        }
    }

    fun changeMinNumberOfPeople(selectedMinNumberOfPeople: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedMinNumberOfPeople = selectedMinNumberOfPeople)
            }
        }
    }

    fun changeMaxNumberOfPeople(selectedMaxNumberOfPeople: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedMaxNumberOfPeople = selectedMaxNumberOfPeople)
            }
        }
    }

    fun changeCost(cost: String) {
        viewModelScope.launch {
            updateState {
                copy(cost = cost)
            }
        }
    }

    fun changeStartDateTime(startDateTime: LocalDateTime) {
        viewModelScope.launch {
            updateState {
                copy(startDateTime = startDateTime)
            }
        }
    }

    fun changeEndDateTime(endDateTime: LocalDateTime) {
        viewModelScope.launch {
            updateState {
                copy(endDateTime = endDateTime)
            }
        }
    }

    fun submitSportEvent() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            val currentState = currentState()
            runCatching {
                when (currentState.screenMode) {
                    is ScreenMode.Edit -> {
                        editSportEventUseCase(
                            sportEventId = currentState.screenMode.sportEventId,
                            title = currentState.title,
                            description = currentState.description,
                            minNumberOfPeople = currentState.selectedMinNumberOfPeople,
                            maxNumberOfPeople = currentState.selectedMaxNumberOfPeople,
                            cost = currentState.cost,
                            startDateTime = currentState.startDateTime,
                            endDateTime = currentState.endDateTime,
                            selectedCoachId = currentState.selectedCoachId!!,
                            selectedRoomId = currentState.selectedRoomId!!,
                            selectedLevelId = currentState.selectedLevelId!!,
                        )
                    }

                    else -> {
                        createSportEventUseCase(
                            title = currentState.title,
                            description = currentState.description,
                            minNumberOfPeople = currentState.selectedMinNumberOfPeople,
                            maxNumberOfPeople = currentState.selectedMaxNumberOfPeople,
                            cost = currentState.cost,
                            startDateTime = currentState.startDateTime,
                            endDateTime = currentState.endDateTime,
                            selectedCoachId = currentState.selectedCoachId!!,
                            selectedRoomId = currentState.selectedRoomId!!,
                            selectedLevelId = currentState.selectedLevelId!!,
                        )
                    }
                }
            }.onSuccess {
                when (currentState.screenMode) {
                    is ScreenMode.Edit -> {
                        viewModelNavigator.popToRoot()
                    }

                    else -> {
                        viewModelNavigator.pop()
                    }
                }
                viewModelNavigator.pop()
            }.onFailure {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
        }
    }
}