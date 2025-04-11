package com.spoonofcode.dojopro.feature.user.di

import com.spoonofcode.dojopro.feature.user.SearchUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val userModule = module {
    viewModelOf(::SearchUserViewModel)
}