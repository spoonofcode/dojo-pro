package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.mockdata.RoleMockData.ROLES
import com.spoonofcode.dojopro.core.data.mockdata.RoleMockData.ROLE_1
import com.spoonofcode.dojopro.core.model.Role
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock

internal fun roleRepositoryMock() = mock<RoleRepository> {
    everySuspend { create(any<Role>()) } returns ROLE_1
    everySuspend { read(any()) } returns ROLE_1
    everySuspend { readAllRolesByUserId(any()) } returns ROLES
}