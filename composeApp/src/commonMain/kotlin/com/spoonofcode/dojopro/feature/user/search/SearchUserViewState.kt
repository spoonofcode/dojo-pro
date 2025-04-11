package com.spoonofcode.dojopro.feature.user.search

import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SearchUserViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = false,
    val searchText: String = "",
    val filteredUsers: List<User> = emptyList(),
): BaseViewState()