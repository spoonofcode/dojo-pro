package com.spoonofcode.dojopro.feature.user.details

import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.ui.BaseViewState

internal data class UserDetailsViewState(
    override val isEnableView: Boolean = true,
    override val isLoadingView: Boolean = true,
    override val isErrorView: Boolean = false,
    val user: User? = null,
    val roles: List<Role>? = null,
    val isVisibleAddCoachRoleButton: Boolean = false,
    val isVisibleAddClubOwnerRoleButton: Boolean = false,
    ): BaseViewState()