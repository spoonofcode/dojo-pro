package model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class SportEvent(
    val id: Int,
    val creationDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val updateDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val title: String,
    val description: String,
    val minNumberOfPeople: Int,
    val maxNumberOfPeople: Int,
    val cost: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val coach: Coach,
    val room: Room,
    val type: Type,
    val level: Level,
    val user: User,
)