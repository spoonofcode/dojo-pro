package com.spoonofcode.dojopro.feature.calendar

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.calendar
import org.jetbrains.compose.resources.StringResource

internal class CalendarScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.calendar
) : BaseScreen<CalendarViewModel, CalendarViewState>() {
    @Composable
    override fun provideViewModel(): CalendarViewModel = koinViewModel<CalendarViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: CalendarViewModel,
        viewState: CalendarViewState
    ): @Composable (ColumnScope.() -> Unit) {
        return ContentView(
            viewState = viewState,
            testMethod = { viewModel.testMethod() },
        )
    }

    @Composable
    private fun ContentView(
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

    // region previews
    // TODO This functions should be private and with @Preview annotation but now
    //  Android Studio will be support previews in commonMain
    @Composable
    fun InitializedCalendarScreenPreview() {
        ContentView(
            content = ContentView(
                viewState = CalendarViewState(title = "TEST"),
                testMethod = {},
            )
        )
    }
    // endregion
}