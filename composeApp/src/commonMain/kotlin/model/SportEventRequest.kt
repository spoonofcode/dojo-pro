package model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class SportEventRequest(
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