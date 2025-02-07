package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.base.repository.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Register
import com.spoonofcode.dojopro.core.model.RegisterRequest

class RegisterRepository : GenericCrudRepository<RegisterRequest, Register>(
    resourceName = "register",
    requestSerializer = RegisterRequest.serializer(),
    responseSerializer = Register.serializer(),
    sessionTokenRequired = false,
)