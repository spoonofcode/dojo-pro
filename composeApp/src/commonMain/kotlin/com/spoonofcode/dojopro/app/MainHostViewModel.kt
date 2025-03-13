package com.spoonofcode.dojopro.app

import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.feature.calendar.CalendarViewState

internal class MainHostViewModel : BaseViewModel<CalendarViewState>(CalendarViewState()) {
}