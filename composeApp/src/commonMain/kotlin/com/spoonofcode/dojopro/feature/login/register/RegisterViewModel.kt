package com.spoonofcode.dojopro.feature.login.register

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.app.MainHostScreen
import com.spoonofcode.dojopro.core.domain.RegisterUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class RegisterViewModel(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegisterViewState>(RegisterViewState()) {

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
        viewModelScope.launch {
            showLoadingView()
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

    private fun showLoadingView() {
        updateState {
            copy(isLoadingView = true)
        }
    }
}