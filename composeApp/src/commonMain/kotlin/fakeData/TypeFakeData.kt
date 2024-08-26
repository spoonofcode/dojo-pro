package fakeData

import model.Type

fun getFakeTypes() = listOf(
    FAKE_TYPE_1,
    FAKE_TYPE_2,
)

val FAKE_TYPE_1 = Type(
    id = 1,
    name = "Stand-up battle",
)

val FAKE_TYPE_2 = Type(
    id = 2,
    name = "Direct fight",
)

val FAKE_TYPE_3 = Type(
    id = 2,
    name = "Kicks",
)