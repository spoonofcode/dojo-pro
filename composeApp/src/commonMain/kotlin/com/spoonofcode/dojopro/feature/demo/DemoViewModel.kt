package com.spoonofcode.dojopro.feature.demo

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import com.spoonofcode.dojopro.core.ui.ext.launchWithProgress

internal class DemoViewModel() : BaseViewModel<DemoViewState>(DemoViewState()) {

    fun initView() {
        viewModelScope.launchWithProgress(
            onProgress = ::setLoadingView
        ) {
        }
    }

    private fun setLoadingView(isLoading: Boolean) {
        updateState {
            copy(isLoadingView = isLoading)
        }
    }

}