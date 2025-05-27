package com.spoonofcode.dojopro.core.data.mockdata

import com.spoonofcode.dojopro.core.model.SportEvent
import kotlinx.datetime.LocalDateTime

object SportEventMockData {
    val SPORT_EVENT_1 = SportEvent(
        id = 1,
        title = "Title 1",
        description = "Description 1",
        minNumberOfPeople = 1,
        maxNumberOfPeople = 1,
        cost = 1,
        startDateTime = LocalDateTime(2023, 1, 1, 1, 1),
        endDateTime = LocalDateTime(2023, 1, 1, 1, 1),
        club = ClubMockData.CLUB_1,
        room = RoomMockData.ROOM_1,
        type = TypeMockData.TYPE_1,
        level = LevelMockData.LEVEL_1,
        creatorUser = UserMockData.USER_1,
    )
    val SPORT_EVENT_2 = SportEvent(
        id = 2,
        title = "Title 2",
        description = "Description 2",
        minNumberOfPeople = 2,
        maxNumberOfPeople = 2,
        cost = 2,
        startDateTime = LocalDateTime(2023, 2, 2, 2, 2),
        endDateTime = LocalDateTime(2023, 2, 2, 2, 2),
        club = ClubMockData.CLUB_2,
        room = RoomMockData.ROOM_2,
        type = TypeMockData.TYPE_2,
        level = LevelMockData.LEVEL_2,
        creatorUser = UserMockData.USER_2,
    )
    val SPORT_EVENT_3 = SportEvent(
        id = 3,
        title = "Title 3",
        description = "Description 3",
        minNumberOfPeople = 3,
        maxNumberOfPeople = 3,
        cost = 3,
        startDateTime = LocalDateTime(2023, 3, 3, 3, 3),
        endDateTime = LocalDateTime(2023, 3, 3, 3, 3),
        club = ClubMockData.CLUB_3,
        room = RoomMockData.ROOM_3,
        type = TypeMockData.TYPE_3,
        level = LevelMockData.LEVEL_3,
        creatorUser = UserMockData.USER_3,
    )

    val SPORT_EVENTS = listOf(SPORT_EVENT_1, SPORT_EVENT_2, SPORT_EVENT_3)
}