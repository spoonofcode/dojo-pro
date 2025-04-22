package com.spoonofcode.dojopro.feature.login.login

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class LoginViewState(
    override val isLoadingView: Boolean = false,
    val email: String = "bartosz.luczak@gmail.com",
    val password: String = "bartosz123",
) : BaseViewState()