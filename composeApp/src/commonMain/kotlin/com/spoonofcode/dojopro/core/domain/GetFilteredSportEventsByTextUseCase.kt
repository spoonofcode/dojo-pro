package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.model.SportEvent

class GetFilteredSportEventsByTextUseCase {
    operator fun invoke(
        searchText: String,
        sportEvents: List<SportEvent>,
    ): List<SportEvent> = sportEvents.filter { sportEvent ->
        sportEvent.title.contains(searchText, ignoreCase = true)
    }
}

