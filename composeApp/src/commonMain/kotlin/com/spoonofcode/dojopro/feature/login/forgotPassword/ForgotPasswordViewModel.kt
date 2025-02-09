package com.spoonofcode.dojopro.feature.login.forgotPassword

import com.spoonofcode.dojopro.core.network.SessionManager
import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch
import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository

internal class ForgotPasswordViewModel(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) : BaseViewModel<ForgotPasswordViewState>(ForgotPasswordViewState()) {

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

    fun resetPassword() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {

        }
    }
}