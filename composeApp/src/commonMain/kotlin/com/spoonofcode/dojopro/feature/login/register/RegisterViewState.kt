package com.spoonofcode.dojopro.feature.login.register

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class RegisterViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
) : BaseViewState()