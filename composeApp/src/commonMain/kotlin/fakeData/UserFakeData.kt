package fakeData

import model.User

fun getFakeUsers() = listOf(
    FAKE_USER_1,
    FAKE_USER_2,
    FAKE_USER_3
)

val FAKE_USER_1 = User(
    id = 1,
    firstName = "Michal",
    lastName = "Staroszczyk",
)

val FAKE_USER_2 = User(
    id = 2,
    firstName = "Bartosz",
    lastName = "Luczak",
)

val FAKE_USER_3 = User(
    id = 2,
    firstName = "Stefan",
    lastName = "Majewski",
)