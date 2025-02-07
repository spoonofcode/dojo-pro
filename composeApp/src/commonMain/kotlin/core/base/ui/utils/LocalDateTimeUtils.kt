package core.base.ui.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object LocalDateTimeUtils {
    fun now() = Clock.System.now().toLocalDateTime(TimeZoneUtils.DEFAULT_ZONE)
}