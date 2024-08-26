package fakeData

import core.ui.utils.LocalDateTimeUtils
import model.SportEvent

fun getFakeSportEvents() = listOf(
    FAKE_SPORT_EVENT_1,
    FAKE_SPORT_EVENT_2,
    FAKE_SPORT_EVENT_3
)

val FAKE_SPORT_EVENT_1 = SportEvent(
    id = 1,
    title = "Sport event 1",
    description = "Sport event 1 - description",
    creationDate = LocalDateTimeUtils.now(),
    updateDate = LocalDateTimeUtils.now(),
    coach = FAKE_COACH_1,
    room = FAKE_ROOM_2,
    minNumberOfPeople = 1,
    maxNumberOfPeople = 10,
    level = FAKE_LEVEL_1,
    type = FAKE_TYPE_1,
    cost = "100",
    startDateTime = LocalDateTimeUtils.now(),
    endDateTime = LocalDateTimeUtils.now(),
    user = FAKE_USER_1,
)

val FAKE_SPORT_EVENT_2 = SportEvent(
    id = 1,
    title = "Sport event 2",
    description = "Sport event 2 - description",
    creationDate = LocalDateTimeUtils.now(),
    updateDate = LocalDateTimeUtils.now(),
    coach = FAKE_COACH_2,
    room = FAKE_ROOM_2,
    minNumberOfPeople = 1,
    maxNumberOfPeople = 10,
    level = FAKE_LEVEL_2,
    type = FAKE_TYPE_2,
    cost = "100",
    startDateTime = LocalDateTimeUtils.now(),
    endDateTime = LocalDateTimeUtils.now(),
    user = FAKE_USER_2,
)

val FAKE_SPORT_EVENT_3 = SportEvent(
    id = 1,
    title = "Sport event 3",
    description = "Sport event 3 - description",
    creationDate = LocalDateTimeUtils.now(),
    updateDate = LocalDateTimeUtils.now(),
    coach = FAKE_COACH_3,
    room = FAKE_ROOM_3,
    minNumberOfPeople = 1,
    maxNumberOfPeople = 10,
    level = FAKE_LEVEL_3,
    type = FAKE_TYPE_3,
    cost = "100",
    startDateTime = LocalDateTimeUtils.now(),
    endDateTime = LocalDateTimeUtils.now(),
    user = FAKE_USER_3,
)