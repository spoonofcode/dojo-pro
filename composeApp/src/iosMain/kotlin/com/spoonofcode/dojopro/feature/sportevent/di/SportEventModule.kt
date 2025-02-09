package com.spoonofcode.dojopro.feature.sportevent.di

import com.spoonofcode.dojopro.feature.sportevent.create.SportEventCreateViewModel
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val sportEventModule = module {
    singleOf(::SportEventCreateViewModel)
    singleOf(::SportEventDetailsViewModel)
}