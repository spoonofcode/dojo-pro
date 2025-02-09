package com.spoonofcode.dojopro.feature.profile.di

import com.spoonofcode.dojopro.feature.profile.ProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val profileModule = module {
    singleOf(::ProfileViewModel)
}