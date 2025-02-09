package com.spoonofcode.dojopro.feature.settings.di

import com.spoonofcode.dojopro.feature.settings.SettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val settingsModule = module {
    singleOf(::SettingsViewModel)
}