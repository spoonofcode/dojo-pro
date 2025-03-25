package com.spoonofcode.dojopro.feature.login.forgotPassword

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository
import com.spoonofcode.dojopro.core.network.SessionManager
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import kotlinx.coroutines.launch

internal class ForgotPasswordViewModel(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) : BaseViewModel<ForgotPasswordViewState>(ForgotPasswordViewState()) {

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

    fun resetPassword() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {

        }
    }
}