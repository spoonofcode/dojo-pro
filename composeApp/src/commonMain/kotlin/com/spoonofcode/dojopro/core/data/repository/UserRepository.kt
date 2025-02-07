package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.base.repository.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.User
import com.spoonofcode.dojopro.core.model.UserRequest

class UserRepository : GenericCrudRepository<UserRequest, User>(
    resourceName = "users",
    requestSerializer = UserRequest.serializer(),
    responseSerializer = User.serializer(),
    sessionTokenRequired = false,
)