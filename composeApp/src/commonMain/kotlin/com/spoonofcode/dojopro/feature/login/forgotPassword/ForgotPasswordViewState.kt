package com.spoonofcode.dojopro.feature.login.forgotPassword

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class ForgotPasswordViewState(
    override val isLoadingView: Boolean = false,
    val email: String = "",
): BaseViewState()