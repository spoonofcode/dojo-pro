package com.spoonofcode.dojopro.feature.calendar.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.spoonofcode.dojopro.feature.calendar.CalendarViewModel

actual val calendarModule = module {
    viewModelOf(::CalendarViewModel)
}