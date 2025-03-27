package com.spoonofcode.dojopro.feature.calendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.spoonofcode.dojopro.core.ui.theme.AppTheme

// TODO This file will be remove when Android Studio will be support previews in commonMain
@PreviewLightDark
@Composable
fun CalendarScreenPreview() {
    AppTheme {
        CalendarScreen().InitializedCalendarScreenPreview()
    }
}