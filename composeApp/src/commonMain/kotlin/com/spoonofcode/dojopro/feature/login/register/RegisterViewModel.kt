package com.spoonofcode.dojopro.feature.login.register

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.app.MainHostScreen
import com.spoonofcode.dojopro.core.domain.RegisterUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch

internal class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegisterViewState>(RegisterViewState()) {

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }

    fun changeEmail(email: String) {
        viewModelScope.launch {
            updateState {
                copy(email = email)
            }
        }
    }

    fun changePassword(password: String) {
        viewModelScope.launch {
            updateState {
                copy(password = password)
            }
        }
    }

    fun changeFirstName(firstName: String) {
        viewModelScope.launch {
            updateState {
                copy(firstName = firstName)
            }
        }
    }

    fun changeLastName(lastName: String) {
        viewModelScope.launch {
            updateState {
                copy(lastName = lastName)
            }
        }
    }

    fun signUp() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                registerUseCase(
                    email = viewState.value.email,
                    password = viewState.value.password,
                    firstName = viewState.value.firstName,
                    lastName = viewState.value.lastName,
                )
            }.onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }
        }
    }
}