package core.ext

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

private val formatter = LocalDateTime.Format {
    date(
        LocalDate.Format {
            dayOfMonth()
            char(' ')
            monthName(MonthNames.ENGLISH_ABBREVIATED)
            chars(" ")
            year()
        }
    )
    chars(" - ")
    time(
        LocalTime.Format {
            hour(); char(':'); minute()
        }
    )
}

fun LocalDateTime.formatedLocalDateTime(): String = this.format(formatter)

