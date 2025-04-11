package com.spoonofcode.dojopro.feature.user.di

import com.spoonofcode.dojopro.feature.user.detail.UserDetailViewModel
import com.spoonofcode.dojopro.feature.user.search.SearchUserViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val userModule = module {
    singleOf(::SearchUserViewModel)
    singleOf(::UserDetailViewModel)
}