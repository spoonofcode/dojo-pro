package com.spoonofcode.dojopro.feature.home.di

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.spoonofcode.dojopro.core.ui.theme.AppTheme
import com.spoonofcode.dojopro.feature.calendar.CalendarScreen

// TODO This file will be remove when Android Studio will be support previews in commonMain
@PreviewFontScale
@PreviewLightDark
@Composable
fun CalendarScreenPreview() {
    AppTheme {
        CalendarScreen().InitializedCalendarScreenPreview()
    }
}