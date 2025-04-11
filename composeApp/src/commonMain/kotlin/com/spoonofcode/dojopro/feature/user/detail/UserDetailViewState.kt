package com.spoonofcode.dojopro.feature.user.detail

import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class UserDetailViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    val searchText: String = "",
    val allUsers: List<User> = emptyList(),
    val filteredUsers: List<User> = emptyList(),
): BaseViewState()