package com.spoonofcode.dojopro.feature.sportevent.di

import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsViewModel
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sportEventModule = module {
    viewModelOf(::SportEventDetailsViewModel)
    viewModelOf(::SportEventEditViewModel)
}