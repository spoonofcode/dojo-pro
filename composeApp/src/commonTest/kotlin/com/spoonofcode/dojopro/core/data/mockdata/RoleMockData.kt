package com.spoonofcode.dojopro.core.data.mockdata

import com.spoonofcode.dojopro.core.model.Role
import com.spoonofcode.dojopro.core.model.Roles

object RoleMockData {
    val ROLE_1 = Role(
        id = Roles.ADMIN.id,
        name = "Role 1",
    )
    val ROLE_2 = Role(
        id = Roles.CLUB_OWNER.id,
        name = "Role 2",
    )
    val ROLE_3 = Role(
        id = Roles.COACH.id,
        name = "Role 3",
    )
    val ROLE_4 = Role(
        id = Roles.USER.id,
        name = "Role 4",
    )

    val ROLES = listOf(ROLE_1, ROLE_2, ROLE_3, ROLE_4)
}