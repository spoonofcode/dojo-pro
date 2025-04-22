package com.spoonofcode.dojopro.feature.shop

import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class ShopViewState(
    override val isLoadingView: Boolean = false,
    val url: String = "https://shop.proof-of-wear.com/"
) : BaseViewState()