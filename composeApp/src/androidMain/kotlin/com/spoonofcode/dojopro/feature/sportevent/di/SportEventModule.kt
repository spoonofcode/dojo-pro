package com.spoonofcode.dojopro.feature.sportevent.di

import com.spoonofcode.dojopro.feature.sportevent.create.SportEventCreateViewModel
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val sportEventModule = module {
    viewModelOf(::SportEventCreateViewModel)
    viewModelOf(::SportEventDetailsViewModel)
}