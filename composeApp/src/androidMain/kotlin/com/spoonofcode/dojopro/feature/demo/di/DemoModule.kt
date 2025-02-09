package com.spoonofcode.dojopro.feature.demo.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.spoonofcode.dojopro.feature.demo.DemoViewModel

actual val demoModule = module {
    viewModelOf(::DemoViewModel)
}