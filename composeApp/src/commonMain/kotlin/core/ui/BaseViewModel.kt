package core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import navigation.ViewModelNavigator
import org.koin.mp.KoinPlatform.getKoin

abstract class BaseViewModel<ViewState>(
    initialViewState: ViewState
) : ViewModel() {

    protected val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    val navigationFlow = viewModelNavigator.navigationEvents

    private val _viewState = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    private val _snackbarEvent = MutableSharedFlow<String>()
    val snackbarEvent = _snackbarEvent.asSharedFlow()

    fun currentState(): ViewState = viewState.value

    fun updateState(
        transformation: ViewState.() -> ViewState,
    ) {
        val currentState = currentState()
        val newState = currentState.transformation()
        _viewState.value = newState
    }

    fun showSnackbar(message: String) {
        viewModelScope.launch {
            _snackbarEvent.emit(message)
        }
    }
}