package com.spoonofcode.dojopro.feature.settings.di

import com.spoonofcode.dojopro.feature.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}