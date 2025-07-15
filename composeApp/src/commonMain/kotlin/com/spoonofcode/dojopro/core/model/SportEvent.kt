package com.spoonofcode.dojopro.core.model

import com.spoonofcode.dojopro.core.base.ext.formatedLocalDateTime
import com.spoonofcode.dojopro.core.base.ext.formatedTime
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlin.time.Duration

@Serializable
data class SportEvent(
    val id: Int,
    val creationDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val updateDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val title: String,
    val description: String,
    val minNumberOfPeople: Int,
    val maxNumberOfPeople: Int,
    val cost: Int,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val club: Club,
    val room: Room,
    val type: Type,
    val level: Level,
    val creatorUser: User,
) {

    fun formatRange(): String {
        val sameDay = startDateTime.date == endDateTime.date
        return if (sameDay) {
            "${startDateTime.formatedLocalDateTime()} - ${endDateTime.formatedTime()}"
        } else {
            "${startDateTime.formatedLocalDateTime()} - ${endDateTime.formatedLocalDateTime()}"
        }
    }

    fun formatRangeWithDurationInMinutes(): String {
        val sameDay = startDateTime.date == endDateTime.date
        return if (sameDay) {
            "${startDateTime.formatedLocalDateTime()} - ${endDateTime.formatedTime()} (${durationMinutes()} min)"
        } else {
            "${startDateTime.formatedLocalDateTime()} - ${endDateTime.formatedLocalDateTime()} (${durationMinutes()} min)"
        }
    }

    /**
     * Length in whole minutes, computed with Kotlin‑time only.
     * You must supply the time‑zone that gives meaning to the LocalDateTimes.
     */
    private fun durationMinutes(zone: TimeZone = TimeZone.UTC): Long {
        val start = startDateTime.toInstant(zone)
        val end = endDateTime.toInstant(zone)
        val d: Duration = end - start   // Kotlin‑time subtraction
        return d.inWholeMinutes
    }
}