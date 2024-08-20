package fakeData

import model.Coach
import model.Room

fun getFakeRooms() = listOf(
    FAKE_ROOM_1,
    FAKE_ROOM_2,
    FAKE_ROOM_3
)

val FAKE_ROOM_1 = Room(
    id = 1,
    name = "DojoRoom1",
)

val FAKE_ROOM_2= Room(
    id = 2,
    name = "DojoRoom3",
)

val FAKE_ROOM_3 = Room(
    id = 3,
    name = "DojoRoom3",
)