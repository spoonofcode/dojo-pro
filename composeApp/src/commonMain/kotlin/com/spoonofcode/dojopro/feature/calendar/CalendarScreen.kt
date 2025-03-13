package com.spoonofcode.dojopro.feature.calendar

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.TabScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel

internal class CalendarScreen : TabScreen<CalendarViewModel, CalendarViewState>() {
    @Composable
    override fun provideViewModel(): CalendarViewModel = koinViewModel<CalendarViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: CalendarViewModel,
        viewState: CalendarViewState
    ): @Composable (ColumnScope.() -> Unit) {
        return InternalContentView(
            viewState = viewState,
            testMethod = { viewModel.testMethod() },
        )
    }

    @Composable
    private fun InternalContentView(
        viewState: CalendarViewState,
        testMethod: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Text(text = viewState.title)
            Text(text = "SD")
            Text(text = "SBB")
            Buttons.PrimaryButton(
                text = "TEST METHOD",
                onClick = testMethod
            )
            Buttons.PrimaryButton(
                text = "TEST METHOD2",
                onClick = testMethod
            )

        }
    }

    @Composable
    fun InitializedCalendarScreenPreview() {
        ContentView(
            content = InternalContentView(
                viewState = CalendarViewState("TEST"),
                testMethod = {},
            )
        )
    }
}