package com.spoonofcode.dojopro.feature.login.di

import com.spoonofcode.dojopro.core.ui.utils.GoogleAuthProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import com.spoonofcode.dojopro.feature.login.login.LoginViewModel
import com.spoonofcode.dojopro.feature.login.register.RegisterViewModel
import com.spoonofcode.dojopro.feature.login.forgotPassword.ForgotPasswordViewModel
import org.koin.core.module.dsl.singleOf

actual val loginModule = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class

    singleOf(::LoginViewModel)
    singleOf(::RegisterViewModel)
    singleOf(::ForgotPasswordViewModel)
}