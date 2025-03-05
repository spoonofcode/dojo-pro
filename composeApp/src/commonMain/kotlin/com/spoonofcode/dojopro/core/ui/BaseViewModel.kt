package com.spoonofcode.dojopro.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.network.NetworkManager
import com.spoonofcode.dojopro.core.ui.navigation.ViewModelNavigator
import dev.jordond.connectivity.Connectivity
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

abstract class BaseViewModel<ViewState>(
    initialViewState: ViewState
) : ViewModel() {

    protected val viewModelNavigator: ViewModelNavigator by getKoin().inject()
    protected val networkManager: NetworkManager by getKoin().inject()

    val navigationFlow = viewModelNavigator.navigationEvents

    private val _viewState = MutableStateFlow(initialViewState)
    val viewState = _viewState.asStateFlow()

    private val _isOnline = MutableStateFlow(false)
    private val isOnline: StateFlow<Boolean> = _isOnline

    private val _snackbarEvent = MutableSharedFlow<SnackbarEvent>()
    val snackbarEvent = _snackbarEvent.asSharedFlow()

    init {
        observeNetworkState()
    }

    fun currentState(): ViewState = viewState.value

    fun updateState(
        transformation: ViewState.() -> ViewState,
    ) {
        val currentState = currentState()
        val newState = currentState.transformation()
        _viewState.value = newState
    }

    fun showSnackbar(message: SnackbarEvent) {
        viewModelScope.launch {
            _snackbarEvent.emit(message)
        }
    }

    private fun observeNetworkState() {
        viewModelScope.launch {
            networkManager.observeNetworkState().collect { status ->
                when (status) {
                    is Connectivity.Status.Connected -> _isOnline.value = true
                    is Connectivity.Status.Disconnected -> _isOnline.value = false
                }
                updateNetworkSnackbar()
            }
        }
    }

    private fun updateNetworkSnackbar() {
        when (isOnline.value) {
            true -> showSnackbar(SnackbarEvent.Online)
            false -> showSnackbar(SnackbarEvent.Offline)
        }
    }
}