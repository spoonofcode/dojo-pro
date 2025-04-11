package com.spoonofcode.dojopro.feature.user.detail

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.domain.GetUserRolesUseCase
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress
import com.spoonofcode.dojopro.feature.sportevent.details.SportEventDetailsScreen
import kotlinx.coroutines.launch

internal class UserDetailViewModel(
    private val getUserRolesUseCase: GetUserRolesUseCase
) : BaseViewModel<UserDetailViewState>(UserDetailViewState()) {

    init {
        initView()
    }

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
        }
    }

    fun selectUser(sportEventId: Int) {
        viewModelScope.launch {
            viewModelNavigator.push(SportEventDetailsScreen(sportEventId = sportEventId))
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }
}