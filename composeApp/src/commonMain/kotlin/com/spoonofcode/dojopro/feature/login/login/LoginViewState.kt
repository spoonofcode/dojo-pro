package com.spoonofcode.dojopro.feature.login.login

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class LoginViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    val email: String = "leo.messi@gmail.com",
    val password: String = "leo123",
) : BaseViewState()