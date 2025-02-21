package com.spoonofcode.dojopro.feature.sportevent.edit

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.data.repository.CoachRepository
import com.spoonofcode.dojopro.core.data.repository.LevelRepository
import com.spoonofcode.dojopro.core.data.repository.RoomRepository
import com.spoonofcode.dojopro.core.domain.SportEventUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

internal class SportEventEditViewModel(
    private val coachRepository: CoachRepository,
    private val roomRepository: RoomRepository,
    private val levelRepository: LevelRepository,
    private val sportEventUseCase: SportEventUseCase
) : BaseViewModel<SportEventEditViewState>(SportEventEditViewState()) {

    fun initView(screenMode: ScreenMode) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                val coachesAsync = async(Dispatchers.IO) { coachRepository.readAll() }
                val roomsAsync = async(Dispatchers.IO) { roomRepository.readAll() }
                val levelsAsync = async(Dispatchers.IO) { levelRepository.readAll() }

                val coaches = coachesAsync.await()
                val rooms = roomsAsync.await()
                val levels = levelsAsync.await()

                Triple(coaches, rooms, levels)
            }.onSuccess { (coaches, rooms, levels) ->
                when (screenMode) {
                    is ScreenMode.Edit -> {
                        val sportEvent =
                            sportEventUseCase.getSportEventById(sportEventId = screenMode.sportEventId)

                        updateState {
                            copy(
                                screenMode = screenMode,
                                coaches = coaches.associate { it.id to it.fullName },
                                rooms = rooms.associate { it.id to it.name },
                                levels = levels.associate { it.id to it.name },
                                selectedCoachId = sportEvent.coach.id,
                                selectedRoomId = sportEvent.room.id,
                                selectedLevelId = sportEvent.level.id,
                                title = sportEvent.title,
                                description = sportEvent.description,
                                selectedMinNumberOfPeople = sportEvent.minNumberOfPeople,
                                selectedMaxNumberOfPeople = sportEvent.maxNumberOfPeople,
                                cost = sportEvent.cost,
                                startDateTime = sportEvent.startDateTime,
                                endDateTime = sportEvent.endDateTime,
                            )
                        }
                    }

                    else -> {
                        updateState {
                            copy(
                                screenMode = screenMode,
                                coaches = coaches.associate { it.id to it.fullName },
                                rooms = rooms.associate { it.id to it.name },
                                levels = levels.associate { it.id to it.name },
                                selectedCoachId = coaches.first().id,
                                selectedRoomId = rooms.first().id,
                                selectedLevelId = levels.first().id,
                            )
                        }
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
                        sportEventUseCase.editSportEvent(
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
                        sportEventUseCase.createSportEvent(
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
                showSnackbar("ERROR: $it")
            }
        }
    }
}