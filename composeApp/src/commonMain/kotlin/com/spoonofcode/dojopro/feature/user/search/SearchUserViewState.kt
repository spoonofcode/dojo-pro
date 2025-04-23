package com.spoonofcode.dojopro.feature.user.search

import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class SearchUserViewState(
    override val isLoadingView: Boolean = false,
    override val isErrorView: Boolean = false,
    val searchText: String = "",
    val initUsers: List<User> = emptyList(),
    val filteredUsers: List<User> = emptyList(),
) : BaseViewState()