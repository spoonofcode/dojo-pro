package com.spoonofcode.dojopro.feature.sportevent.di

import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsViewModel
import com.spoonofcode.dojopro.feature.sportevent.edit.SportEventEditViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val sportEventModule = module {
    viewModelOf(::SportEventEditViewModel)
    viewModelOf(::SportEventDetailsViewModel)
}