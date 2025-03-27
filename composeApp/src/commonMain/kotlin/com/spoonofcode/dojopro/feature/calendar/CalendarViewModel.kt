package com.spoonofcode.dojopro.feature.calendar

import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.SnackbarEvent

internal class CalendarViewModel : BaseViewModel<CalendarViewState>(CalendarViewState()) {

    fun testMethod(){
        showSnackbar(SnackbarEvent.Error(message = "TEST BARTEK"))

        updateState {
            copy(isLoadingView = true)
        }
    }
}