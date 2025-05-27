package com.spoonofcode.dojopro.feature.search.di

import com.spoonofcode.dojopro.feature.search.filter.FilterViewModel
import com.spoonofcode.dojopro.feature.search.search.SearchViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::FilterViewModel)
}