package com.spoonofcode.dojopro.feature.shop.di

import com.spoonofcode.dojopro.feature.shop.ShopViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val shopModule = module {
    viewModelOf(::ShopViewModel)
}