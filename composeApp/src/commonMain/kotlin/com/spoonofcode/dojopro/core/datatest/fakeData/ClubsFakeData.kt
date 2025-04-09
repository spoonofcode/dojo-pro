package com.spoonofcode.dojopro.core.datatest.fakeData

import com.spoonofcode.dojopro.core.model.Club

fun getFakeClubs() = listOf(
    FAKE_CLUB_1,
    FAKE_CLUB_2,
    FAKE_CLUB_3
)

val FAKE_CLUB_1 = Club(
    id = 1,
    name = "Club 1",
    location = "Wroclaw",
)

val FAKE_CLUB_2 = Club(
    id = 2,
    name = "Club 2",
    location = "Siechnice",
)

val FAKE_CLUB_3 = Club(
    id = 3,
    name = "Club 3",
    location = "Wysoka",
)