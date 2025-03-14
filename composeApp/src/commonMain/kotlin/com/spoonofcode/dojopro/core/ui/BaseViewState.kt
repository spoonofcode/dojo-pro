package com.spoonofcode.dojopro.core.ui

abstract class BaseViewState(
    open val isEnableView: Boolean = true,
    open val isLoadingView: Boolean = false,
    open val isErrorView: Boolean = false,
)