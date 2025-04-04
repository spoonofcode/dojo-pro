package com.spoonofcode.dojopro.feature.shop

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class ShopViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val url: String = "https://shop.proof-of-wear.com/"
) : BaseViewState()