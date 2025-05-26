package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.mockdata.ClubMockData.CLUBS
import com.spoonofcode.dojopro.core.data.mockdata.ClubMockData.CLUB_1
import com.spoonofcode.dojopro.core.model.Club
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun clubRepositoryMock() = mock<ClubRepository> {
    everySuspend { create(any<Club>()) } returns CLUB_1
    everySuspend { read(any()) } returns CLUB_1
    everySuspend { readAll() } returns CLUBS
}