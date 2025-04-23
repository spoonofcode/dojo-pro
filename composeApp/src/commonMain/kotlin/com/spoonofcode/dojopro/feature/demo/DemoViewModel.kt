package com.spoonofcode.dojopro.feature.demo

import androidx.lifecycle.viewModelScope
import com.spoonofcode.dojopro.core.ui.BaseViewModel
import kotlinx.coroutines.launch

internal class DemoViewModel() : BaseViewModel<DemoViewState>(DemoViewState()) {

    fun initView() {
        viewModelScope.launch {
        }
    }

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

}