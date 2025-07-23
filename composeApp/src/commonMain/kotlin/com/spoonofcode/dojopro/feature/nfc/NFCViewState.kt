package com.spoonofcode.dojopro.feature.nfc

import com.spoonofcode.dojopro.core.ui.BaseViewState

data class NFCViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
) : BaseViewState()