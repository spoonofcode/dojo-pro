package tabs.search

import androidx.lifecycle.viewModelScope
import core.ui.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import model.SportEventRequest
import repository.SportEventRepository

internal class SearchViewModel(
    private val sportEventRepository: SportEventRepository,
) : BaseViewModel<SearchViewState>(SearchViewState()) {

    fun getSportEvents() {
        viewModelScope.launch {
            sportEventRepository.create(
                SportEventRequest(
                    title = "Corrie",
                    description = "Dezarae",
                    minNumberOfPeople = 4,
                    maxNumberOfPeople = 12,
                    cost = "Raheem",
                    startDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
                    endDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
                    coachId = 1,
                    roomId = 1,
                    typeId = 1,
                    levelId = 1,
                    userId = 1
                )
            )
        }

        viewModelScope.launch {
            val sportEvents = sportEventRepository.readAll()
            updateState {
                copy(
                    sportEvents = sportEvents
                )
            }
        }
    }

}