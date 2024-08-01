package fakeData

import model.Level

fun getFakeTypes() = listOf(
    FAKE_TYPE_1,
    FAKE_TYPE_2,
)

private val FAKE_TYPE_1 = Level(
    id = 1,
    name = "Individual",
)

private val FAKE_TYPE_2 = Level(
    id = 2,
    name = "Group",
)