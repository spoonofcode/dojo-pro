package tabs.sportevent.create

import androidx.lifecycle.viewModelScope
import core.ext.formatedLocalDateTime
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDateTime
import model.SportEventRequest
import repository.CoachRepository
import repository.LevelRepository
import repository.RoomRepository
import repository.SportEventRepository

internal class SportEventCreateViewModel(
    private val coachRepository: CoachRepository,
    private val roomRepository: RoomRepository,
    private val levelRepository: LevelRepository,
    private val sportEventRepository: SportEventRepository,
) : BaseViewModel<SportEventCreateViewState>(SportEventCreateViewState()) {

    fun initView() {
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
                updateState {
                    copy(
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
        println("BARTEK startDateTime = ${startDateTime.formatedLocalDateTime()}")
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

    fun createSportEvent() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                withContext(Dispatchers.IO) {
                    val currentState = currentState()
                    sportEventRepository.create(
                        request = SportEventRequest(
                            title = currentState.title,
                            description = currentState.description,
                            minNumberOfPeople = currentState.selectedMinNumberOfPeople,
                            maxNumberOfPeople = currentState.selectedMaxNumberOfPeople,
                            cost = currentState.cost,
                            startDateTime = currentState.startDateTime,
                            endDateTime = currentState.endDateTime,
                            coachId = currentState.selectedCoachId!!,
                            roomId = currentState.selectedRoomId!!,
                            typeId = 1,
                            levelId = currentState.selectedLevelId!!,
                            userId = 1,
                        )
                    )
                }
            }
        }
    }
}