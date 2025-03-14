package com.spoonofcode.dojopro.feature.login.forgotPassword

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class ForgotPasswordViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = false,
    val email: String = "",
): BaseViewState()