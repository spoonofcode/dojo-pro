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
    email = "michal.staroszczyk@gmail.com"
)

val FAKE_USER_2 = User(
    id = 2,
    firstName = "Bartosz",
    lastName = "Luczak",
    email = "bartosz.luczak@gmail.com"
)

val FAKE_USER_3 = User(
    id = 2,
    firstName = "Stefan",
    lastName = "Majewski",
    email = "stefan.majewski@gmail.com"
)