package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENTS
import com.spoonofcode.dojopro.core.data.mockdata.SportEventMockData.SPORT_EVENT_1
import com.spoonofcode.dojopro.core.model.SportEventRequest
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun sportEventRepositoryMock() = mock<SportEventRepository>(MockMode.autoUnit) {
    everySuspend { create(any<SportEventRequest>()) } returns SPORT_EVENT_1
    everySuspend { read(any()) } returns SPORT_EVENT_1
    everySuspend { update(any(), any()) } returns true
    everySuspend { delete(any()) } returns true
    everySuspend { readAll() } returns SPORT_EVENTS
    everySuspend { readSportEventsCreatedByUser(any()) } returns SPORT_EVENTS
}