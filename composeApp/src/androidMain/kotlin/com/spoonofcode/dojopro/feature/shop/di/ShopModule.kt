package com.spoonofcode.dojopro.feature.shop.di

import com.spoonofcode.dojopro.feature.shop.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val shopModule = module {
    viewModelOf(::ShopViewModel)
}