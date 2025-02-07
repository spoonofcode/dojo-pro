package com.spoonofcode.dojopro

import org.koin.core.module.dsl.singleOf
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
    singleOf(::CalendarViewModel)
    singleOf(::HomeViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::SearchViewModel)
    singleOf(::ShopViewModel)

    singleOf(::SportEventCreateViewModel)
    singleOf(::SportEventDetailsViewModel)
    singleOf(::SettingsViewModel)

    singleOf(::LoginViewModel)
    singleOf(::RegisterViewModel)
    singleOf(::ForgotPasswordViewModel)
}