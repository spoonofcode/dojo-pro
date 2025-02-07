package com.spoonofcode.dojopro

import com.spoonofcode.dojopro.core.ui.utils.GoogleAuthProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val authModule = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}