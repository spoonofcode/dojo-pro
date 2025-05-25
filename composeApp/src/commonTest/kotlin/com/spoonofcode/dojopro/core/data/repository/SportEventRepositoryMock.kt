package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENTS
import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENT_1
import com.spoonofcode.dojopro.core.model.SportEventRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun sportEventRepositoryMock() = mock<SportEventRepository> {
    everySuspend { create(any<SportEventRequest>()) } returns SPORT_EVENT_1
    everySuspend { read(any()) } returns SPORT_EVENT_1
    everySuspend { readSportEventsCreatedByUser(any()) } returns SPORT_EVENTS
}