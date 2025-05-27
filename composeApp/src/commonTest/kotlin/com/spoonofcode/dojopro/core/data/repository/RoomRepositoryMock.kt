package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.mockdata.RoomMockData.ROOMS
import com.spoonofcode.dojopro.core.data.mockdata.RoomMockData.ROOM_1
import com.spoonofcode.dojopro.core.model.Room
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun roomRepositoryMock() = mock<RoomRepository> {
    everySuspend { create(any<Room>()) } returns ROOM_1
    everySuspend { read(any()) } returns ROOM_1
    everySuspend { readAll() } returns ROOMS
}