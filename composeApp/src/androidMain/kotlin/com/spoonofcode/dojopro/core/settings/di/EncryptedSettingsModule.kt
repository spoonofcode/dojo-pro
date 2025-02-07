package com.spoonofcode.dojopro.core.settings.di

import com.spoonofcode.dojopro.core.settings.createEncryptedSettings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val encryptedSettingsModule = module {
    singleOf(::createEncryptedSettings)
}