package com.spoonofcode.dojopro.feature.user.di

import com.spoonofcode.dojopro.feature.user.SearchUserViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val userModule = module {
    singleOf(::SearchUserViewModel)
}