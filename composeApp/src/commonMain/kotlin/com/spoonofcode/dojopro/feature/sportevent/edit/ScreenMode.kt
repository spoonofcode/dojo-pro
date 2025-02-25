package com.spoonofcode.dojopro.feature.sportevent.edit

sealed class ScreenMode {
    object Create : ScreenMode()
    data class Edit(val sportEventId: Int) : ScreenMode()
}