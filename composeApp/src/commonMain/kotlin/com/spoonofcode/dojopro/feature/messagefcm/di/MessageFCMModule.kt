package com.spoonofcode.dojopro.feature.messagefcm.di

import com.spoonofcode.dojopro.feature.messagefcm.MessageFCMViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val messageFCMModule = module {
    viewModelOf(::MessageFCMViewModel)
}