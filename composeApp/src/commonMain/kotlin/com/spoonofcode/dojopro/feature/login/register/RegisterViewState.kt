package com.spoonofcode.dojopro.feature.login.register

internal data class RegisterViewState(
    val isViewEnable: Boolean = true,
    val isViewLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
)