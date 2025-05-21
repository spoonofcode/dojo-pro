package com.spoonofcode.dojopro.feature.home.di

import com.spoonofcode.dojopro.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeViewModel)
}