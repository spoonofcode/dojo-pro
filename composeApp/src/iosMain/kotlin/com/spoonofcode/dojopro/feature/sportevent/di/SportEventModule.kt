package com.spoonofcode.dojopro.feature.sportevent.di

import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsViewModel
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val sportEventModule = module {
    singleOf(::SportEventEditViewModel)
    singleOf(::SportEventDetailsViewModel)
}