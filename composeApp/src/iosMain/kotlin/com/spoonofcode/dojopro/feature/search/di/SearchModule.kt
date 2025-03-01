package com.spoonofcode.dojopro.feature.search.di

import com.spoonofcode.dojopro.feature.search.SearchViewModel
import com.spoonofcode.dojopro.feature.search.filter.FilterViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val searchModule = module {
    singleOf(::SearchViewModel)
    singleOf(::FilterViewModel)
}