package model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class SportEvent(
    val id: Int,
    val title: String,
    val description: String,
    val creationDate: LocalDateTime,
    val updateDate: LocalDateTime,
    val coach: Coach,
    val room: Room,
    val minNumberOfPeople: Int,
    val maxNumberOfPeople: Int,
    val level: Level,
    val type: String,
    val cost: String,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
)

