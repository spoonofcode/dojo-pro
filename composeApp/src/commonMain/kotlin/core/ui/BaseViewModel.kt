package core.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.mp.KoinPlatform.getKoin
import navigation.ViewModelNavigator

abstract class BaseViewModel<ViewState>(
    initialViewState: ViewState
) : ViewModel() {

    protected val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    val navigationFlow = viewModelNavigator.navigationEvents

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