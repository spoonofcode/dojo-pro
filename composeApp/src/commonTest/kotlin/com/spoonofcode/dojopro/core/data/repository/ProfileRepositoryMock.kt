package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.model.ProfileRequest
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun profileRepositoryMock() = mock<ProfileRepository> {
    everySuspend { create(any<ProfileRequest>()) } returns Profile(
        name = "dsa",
        numberOfEventsUserParticipatedIn = 1,
        numberOfEventsCreatedByUser = 1
    )
    everySuspend { read(any()) } returns Profile(
        name = "Profile name 1",
        numberOfEventsUserParticipatedIn = 1,
        numberOfEventsCreatedByUser = 1
    )
}