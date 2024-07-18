package core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<ViewState>(
    initialViewState: ViewState
) : ViewModel() {
    private val _viewState = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    fun currentState(): ViewState = viewState.value

    fun updateState(
        transformation: ViewState.() -> ViewState,
    ) {
        val currentState = currentState()
        val newState = currentState.transformation()
        _viewState.value = newState
    }
}