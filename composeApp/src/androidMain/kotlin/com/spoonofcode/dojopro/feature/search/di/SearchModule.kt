package com.spoonofcode.dojopro.feature.search.di

import com.spoonofcode.dojopro.feature.search.SearchViewModel
import com.spoonofcode.dojopro.feature.search.filter.FilterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

actual val searchModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::FilterViewModel)
}