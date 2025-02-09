package com.spoonofcode.dojopro.feature.calendar.di

import com.spoonofcode.dojopro.feature.calendar.CalendarViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val calendarModule = module {
    singleOf(::CalendarViewModel)
}