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
    val coachId: Int,
    val roomId: Int,
    val typeId: Int,
    val levelId: Int,
    val userId: Int,
)