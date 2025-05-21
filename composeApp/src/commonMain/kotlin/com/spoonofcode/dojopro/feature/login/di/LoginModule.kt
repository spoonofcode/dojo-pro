package com.spoonofcode.dojopro.feature.login.di

import com.spoonofcode.dojopro.feature.login.forgotPassword.ForgotPasswordViewModel
import com.spoonofcode.dojopro.feature.login.login.LoginViewModel
import com.spoonofcode.dojopro.feature.login.register.RegisterViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformLoginModule: Module

val loginModule: Module = module {
    includes(platformLoginModule)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::ForgotPasswordViewModel)
}