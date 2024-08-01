package fakeData

import model.Level

fun getFakeLevels() = listOf(
    FAKE_LEVEL_1,
    FAKE_LEVEL_2,
    FAKE_LEVEL_3
)

private val FAKE_LEVEL_1 = Level(
    id = 1,
    name = "Basic",
)

private val FAKE_LEVEL_2 = Level(
    id = 2,
    name = "Advance",
)

private val FAKE_LEVEL_3 = Level(
    id = 3,
    name = "Pro",
)