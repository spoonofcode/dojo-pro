package com.spoonofcode.dojopro.core.network

import com.spoonofcode.dojopro.core.data.mockdata.UserMockData.USER_1
import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.mock

fun sessionManagerMock() = mock<SessionManager>(mode = MockMode.autoUnit) {
    every { getSessionUserId() } returns USER_1.id
}