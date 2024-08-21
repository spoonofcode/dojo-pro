package tabs.sportevent.create

import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import core.ui.ext.launchWithProgress
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import repository.CoachRepository
import repository.LevelRepository
import repository.ProfileRepository
import repository.RoomRepository

internal class SportEventCreateViewModel(
    private val profileRepository: ProfileRepository,
    private val coachRepository: CoachRepository,
    private val roomRepository: RoomRepository,
    private val levelRepository: LevelRepository,
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
                        coaches = coaches,
                        rooms = rooms,
                        levels = levels,
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

    }

}