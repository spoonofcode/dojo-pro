package com.spoonofcode.dojopro.feature.login.login

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.LoginUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch
import com.spoonofcode.dojopro.feature.login.forgotPassword.ForgotPasswordScreen
import com.spoonofcode.dojopro.app.MainHostScreen
import com.spoonofcode.dojopro.feature.login.register.RegisterScreen

internal class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginViewState>(LoginViewState()) {

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isViewLoading = isLoading)
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

    fun forgotPassword() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            viewModelNavigator.push(ForgotPasswordScreen())
        }
    }

    fun signIn() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                loginUseCase.signIn(
                    email = viewState.value.email,
                    password = viewState.value.password,
                )
            }.onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showSnackbar("ERROR: $it")
            }
        }
    }

    fun signInWithGoogle(googleIdToken: String) {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            runCatching {
                loginUseCase.signInWithGoogle(googleIdToken)
            }.onSuccess {
                viewModelNavigator.replaceAll(listOf(MainHostScreen()))
            }.onFailure {
                showSnackbar("ERROR: $it")
            }
        }
    }

    fun signUp() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
            viewModelNavigator.push(RegisterScreen())
        }
    }
}