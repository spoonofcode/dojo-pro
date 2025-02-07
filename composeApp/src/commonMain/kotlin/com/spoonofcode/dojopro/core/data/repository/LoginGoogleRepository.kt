package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.base.repository.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.LoginGoogle
import com.spoonofcode.dojopro.core.model.LoginGoogleRequest

class LoginGoogleRepository : GenericCrudRepository<LoginGoogleRequest, LoginGoogle>(
    resourceName = "login/google",
    requestSerializer = LoginGoogleRequest.serializer(),
    responseSerializer = LoginGoogle.serializer(),
    sessionTokenRequired = false,
)