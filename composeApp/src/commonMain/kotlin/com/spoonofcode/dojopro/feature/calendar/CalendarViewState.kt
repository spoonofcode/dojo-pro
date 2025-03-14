package com.spoonofcode.dojopro.feature.calendar

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class CalendarViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val title: String = "Calendar title"
) : BaseViewState(
    isEnableView = isEnableView,
    isLoadingView = isLoadingView,
    isErrorView = isErrorView
)