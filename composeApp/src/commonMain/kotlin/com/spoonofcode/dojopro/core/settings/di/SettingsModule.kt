package com.spoonofcode.dojopro.core.settings.di

import com.spoonofcode.dojopro.feature.settings.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformSettingsModule: Module

val settingsModule: Module = module {
    includes(platformSettingsModule)
    viewModelOf(::SettingsViewModel)
}