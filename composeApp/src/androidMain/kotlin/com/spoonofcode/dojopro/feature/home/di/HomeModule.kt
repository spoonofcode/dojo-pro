package com.spoonofcode.dojopro.feature.home.di

import com.spoonofcode.dojopro.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val homeModule = module {
    viewModelOf(::HomeViewModel)
}