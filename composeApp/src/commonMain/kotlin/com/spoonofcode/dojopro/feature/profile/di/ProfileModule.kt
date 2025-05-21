package com.spoonofcode.dojopro.feature.profile.di

import com.spoonofcode.dojopro.feature.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::ProfileViewModel)
}