package com.spoonofcode.dojopro.feature.demo.di

import com.spoonofcode.dojopro.feature.demo.DemoViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val demoModule = module {
    singleOf(::DemoViewModel)
}