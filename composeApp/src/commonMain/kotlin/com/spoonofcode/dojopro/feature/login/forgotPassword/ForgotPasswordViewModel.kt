package com.spoonofcode.dojopro.feature.login.forgotPassword

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.data.repository.LoginGoogleRepository
import com.spoonofcode.dojopro.core.network.SessionManager
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class ForgotPasswordViewModel(
    private val loginGoogleRepository: LoginGoogleRepository,
    private val sessionManager: SessionManager,
) : BaseViewModel<ForgotPasswordViewState>(ForgotPasswordViewState()) {

    fun changeEmail(email: String) {
        viewModelScope.launch {
            updateState {
                copy(
                    email = email,
                    isLoadingView = false,
                )
            }
        }
    }

    fun resetPassword() {
        viewModelScope.launch {

        }
    }
}