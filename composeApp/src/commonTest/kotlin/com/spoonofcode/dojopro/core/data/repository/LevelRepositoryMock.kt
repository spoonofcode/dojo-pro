package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.mockdata.LevelMockData.LEVELS
import com.spoonofcode.dojopro.core.data.mockdata.LevelMockData.LEVEL_1
import com.spoonofcode.dojopro.core.model.Level
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun levelRepositoryMock() = mock<LevelRepository> {
    everySuspend { create(any<Level>()) } returns LEVEL_1
    everySuspend { read(any()) } returns LEVEL_1
    everySuspend { readAll() } returns LEVELS
}