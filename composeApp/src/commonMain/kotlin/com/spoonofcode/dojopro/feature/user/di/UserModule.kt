package com.spoonofcode.dojopro.feature.user.di

import com.spoonofcode.dojopro.feature.user.details.UserDetailsViewModel
import com.spoonofcode.dojopro.feature.user.search.SearchUserViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val userModule = module {
    viewModelOf(::UserDetailsViewModel)
    viewModelOf(::SearchUserViewModel)
}