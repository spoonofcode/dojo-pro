package com.spoonofcode.dojopro.feature.login.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.app.MainHostScreen
import com.spoonofcode.dojopro.core.domain.LoginGoogleUseCase
import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.SnackbarEvent
import com.spoonofcode.dojopro.feature.login.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.dojopro.feature.login.register.RegisterScreen
import kotlinx.coroutines.launch

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val loginGoogleUseCase: LoginGoogleUseCase,
) : BaseViewModel<LoginViewState>(LoginViewState()) {

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

    fun forgotPassword() {
        viewModelScope.launch {
            viewModelNavigator.push(ForgotPasswordScreen())
        }
    }

    fun signIn() {
        viewModelScope.launch {
            runCatching {
                showLoadingView()
                loginUseCase(
                    email = viewState.value.email,
                    password = viewState.value.password,
                )
            }.onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
        }
    }

    fun signInWithGoogle(googleIdToken: String) {
        viewModelScope.launch {
            showLoadingView()
            runCatching {
                loginGoogleUseCase(googleIdToken)
            }.onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showSnackbar(SnackbarEvent.Error(message = "ERROR: $it"))
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            showLoadingView()
            viewModelNavigator.push(RegisterScreen())
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(isLoadingView = true)
        }
    }
}