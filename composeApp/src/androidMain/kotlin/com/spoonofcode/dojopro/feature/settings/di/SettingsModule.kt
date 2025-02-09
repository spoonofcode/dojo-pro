package com.spoonofcode.dojopro.feature.settings.di

import com.spoonofcode.dojopro.feature.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val settingsModule = module {
    viewModelOf(::SettingsViewModel)
}