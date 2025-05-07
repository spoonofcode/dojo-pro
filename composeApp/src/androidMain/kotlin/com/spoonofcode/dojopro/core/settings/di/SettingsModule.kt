package com.spoonofcode.dojopro.core.settings.di

import com.spoonofcode.dojopro.core.settings.createEncryptedSettings
import com.spoonofcode.dojopro.feature.appsettings.AppSettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val settingsModule = module {
    viewModelOf(::AppSettingsViewModel)
    singleOf(::createEncryptedSettings)
}