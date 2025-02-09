package com.spoonofcode.dojopro.feature.shop.di

import com.spoonofcode.dojopro.core.ui.utils.GoogleAuthProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.spoonofcode.dojopro.feature.shop.ShopViewModel
import org.koin.core.module.dsl.singleOf

actual val shopModule = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
    singleOf(::ShopViewModel)
}