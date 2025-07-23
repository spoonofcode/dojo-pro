package com.spoonofcode.dojopro.feature.nfc.di

import com.spoonofcode.dojopro.feature.nfc.NFCViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val nfcModule = module {
    viewModelOf(::NFCViewModel)
}