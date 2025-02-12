package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Login
import com.spoonofcode.dojopro.core.model.LoginRequest

class LoginRepository : GenericCrudRepository<LoginRequest, Login>(
    resourceName = "login",
    requestSerializer = LoginRequest.serializer(),
    responseSerializer = Login.serializer(),
    sessionTokenRequired = false,
)