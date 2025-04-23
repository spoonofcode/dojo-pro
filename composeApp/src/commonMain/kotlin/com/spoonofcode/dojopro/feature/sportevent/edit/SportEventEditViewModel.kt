package com.spoonofcode.dojopro.feature.sportevent.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.CreateSportEventUseCase
import com.spoonofcode.dojopro.core.domain.EditSportEventUseCase
import com.spoonofcode.dojopro.core.domain.LoadSportEventFormDataUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

internal class SportEventEditViewModel(
    private val createSportEventUseCase: CreateSportEventUseCase,
    private val editSportEventUseCase: EditSportEventUseCase,
    private val loadSportEventFormDataUseCase: LoadSportEventFormDataUseCase,
) : BaseViewModel<SportEventEditViewState>(SportEventEditViewState()) {

    fun initView(screenMode: ScreenMode) {
        viewModelScope.launch {
            showLoadingView()
            runCatching {
                loadSportEventFormDataUseCase(screenMode = screenMode)
            }.onSuccess { sportEventFormData ->
                if (sportEventFormData.sportEvent == null) {
                    updateState {
                        copy(
                            isLoadingView = false,
                            screenMode = screenMode,
                            clubs = sportEventFormData.clubs.associate { it.id to it.name },
                            coaches = sportEventFormData.coaches.associate { it.id to it.fullName },
                            rooms = sportEventFormData.rooms.associate { it.id to it.name },
                            levels = sportEventFormData.levels.associate { it.id to it.name },
                            types = sportEventFormData.types.associate { it.id to it.name },
                            selectedClubId = sportEventFormData.clubs.first().id,
                            selectedCoachId = sportEventFormData.coaches.first().id,
                            selectedRoomId = sportEventFormData.rooms.first().id,
                            selectedLevelId = sportEventFormData.levels.first().id,
                            selectedTypeId = sportEventFormData.types.first().id,
                        )
                    }
                } else {
                    updateState {
                        copy(
                            isLoadingView = false,
                            screenMode = screenMode,
                            clubs = sportEventFormData.clubs.associate { it.id to it.name },
                            coaches = sportEventFormData.coaches.associate { it.id to it.fullName },
                            rooms = sportEventFormData.rooms.associate { it.id to it.name },
                            levels = sportEventFormData.levels.associate { it.id to it.name },
                            types = sportEventFormData.types.associate { it.id to it.name },
                            selectedClubId = sportEventFormData.sportEvent.club.id,
                            selectedCoachId = sportEventFormData.sportEvent.creatorUser.id,
                            selectedRoomId = sportEventFormData.sportEvent.room.id,
                            selectedLevelId = sportEventFormData.sportEvent.level.id,
                            selectedTypeId = sportEventFormData.sportEvent.type.id,
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

    fun changeClub(selectedClubId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedClubId = selectedClubId)
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

    fun changeType(selectedTypeId: Int) {
        viewModelScope.launch {
            updateState {
                copy(selectedTypeId = selectedTypeId)
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
        viewModelScope.launch {
            showLoadingView()
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
                            selectedClubId = currentState.selectedClubId!!,
                            selectedCoachId = currentState.selectedCoachId!!,
                            selectedRoomId = currentState.selectedRoomId!!,
                            selectedLevelId = currentState.selectedLevelId!!,
                            selectedTypeId = currentState.selectedTypeId!!,
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
                            selectedClubId = currentState.selectedClubId!!,
                            selectedCoachId = currentState.selectedCoachId!!,
                            selectedRoomId = currentState.selectedRoomId!!,
                            selectedLevelId = currentState.selectedLevelId!!,
                            selectedTypeId = currentState.selectedTypeId!!,
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

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }
}