package fakeData

import model.Coach

fun getFakeCoaches() = listOf(
    FAKE_COACH_1,
    FAKE_COACH_2,
    FAKE_COACH_3
)

private val FAKE_COACH_1 = Coach(
    id = 1,
    firstName = "Michal",
    lastName = "Staroszczyk",
)

private val FAKE_COACH_2 = Coach(
    id = 2,
    firstName = "Kazimierz",
    lastName = "Gorski",
)

private val FAKE_COACH_3 = Coach(
    id = 2,
    firstName = "Stefan",
    lastName = "Majewski",
)