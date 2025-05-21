package com.spoonofcode.dojopro.feature.demo.di

import com.spoonofcode.dojopro.feature.demo.DemoViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val demoModule = module {
    viewModelOf(::DemoViewModel)
}