package com.spoonofcode.dojopro.feature.search

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.data.repository.SportEventRepository
import com.spoonofcode.dojopro.core.model.SportEventRequest
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

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
                    creatorUserId = 1
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