package com.spoonofcode.dojopro.core.domain

import com.spoonofcode.dojopro.core.model.User

class GetFilteredUsersByTextUseCase {
    operator fun invoke(
        searchText: String,
        users: List<User>,
    ): List<User> = users.filter { user ->
        user.fullName.contains(searchText, ignoreCase = true)
    }
}

