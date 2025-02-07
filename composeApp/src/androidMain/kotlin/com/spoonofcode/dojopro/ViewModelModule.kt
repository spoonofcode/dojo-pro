package com.spoonofcode.dojopro

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.spoonofcode.dojopro.feature.calendar.CalendarViewModel
import com.spoonofcode.dojopro.feature.login.forgotPassword.ForgotPasswordViewModel
import com.spoonofcode.dojopro.feature.home.HomeViewModel
import com.spoonofcode.dojopro.feature.login.login.LoginViewModel
import com.spoonofcode.dojopro.feature.profile.ProfileViewModel
import com.spoonofcode.dojopro.feature.login.register.RegisterViewModel
import com.spoonofcode.dojopro.feature.search.SearchViewModel
import com.spoonofcode.dojopro.feature.settings.SettingsViewModel
import com.spoonofcode.dojopro.feature.shop.ShopViewModel
import com.spoonofcode.dojopro.feature.sportevent.create.SportEventCreateViewModel
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsViewModel

actual val viewModelModule = module {
    viewModelOf(::CalendarViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ShopViewModel)

    viewModelOf(::SportEventCreateViewModel)
    viewModelOf(::SportEventDetailsViewModel)
    viewModelOf(::SettingsViewModel)

    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}