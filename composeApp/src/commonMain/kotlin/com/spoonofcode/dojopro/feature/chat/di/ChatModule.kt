package com.spoonofcode.dojopro.feature.chat.di

import com.spoonofcode.dojopro.feature.chat.ChatViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val chatModule = module {
    viewModelOf(::ChatViewModel)
}