package com.spoonofcode.dojopro.feature.chat

import com.spoonofcode.dojopro.core.ui.BaseViewState

data class ChatViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val remoteToken: String = "",
    val messageTitle: String = "",
    val messageText: String = "",
    val firebaseMessageToken: String = "",
) : BaseViewState()