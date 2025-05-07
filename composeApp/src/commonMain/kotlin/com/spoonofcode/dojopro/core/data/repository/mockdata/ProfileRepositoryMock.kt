package com.spoonofcode.dojopro.core.data.repository.mockdata

import com.spoonofcode.dojopro.core.data.repository.ProfileRepositoryBartek
import io.mockative.mock
import io.mockative.of

val profileRepositoryMock: ProfileRepositoryBartek = mock(of<ProfileRepositoryBartek>())
//    .apply {
//        runBlocking {
//            coEvery { create(any<ProfileRequest>()) } returns Profile(
//                name = "dsa",
//                numberOfEventsUserParticipatedIn = 1,
//                numberOfEventsCreatedByUser = 1
//            )
//        }
//    }
//    .apply {
//        runBlocking {
//            coEvery { read(any()) } returns Profile(
//                name = "dsa",
//                numberOfEventsUserParticipatedIn = 1,
//                numberOfEventsCreatedByUser = 1
//            )
//        }
//    }
//    .apply { every { testBartek() } returns "xDSA BARTEK" }