package com.spoonofcode.dojopro.feature.nfc

import com.spoonofcode.dojopro.core.ui.BaseViewModel

class NFCViewModel() : BaseViewModel<NFCViewState>(NFCViewState()) {

    private fun showLoadingView() {
        updateState {
            copy(
                isLoadingView = true,
                isErrorView = false,
            )
        }
    }

    private fun showErrorView() {
        updateState {
            copy(
                isLoadingView = false,
                isErrorView = true,
            )
        }
    }
}