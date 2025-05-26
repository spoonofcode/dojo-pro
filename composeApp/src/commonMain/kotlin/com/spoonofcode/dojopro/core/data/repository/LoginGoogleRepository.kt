package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.LoginGoogle
import com.spoonofcode.dojopro.core.model.LoginGoogleRequest
import com.spoonofcode.dojopro.core.test.OpenForMokkery

@OpenForMokkery
class LoginGoogleRepository : GenericCrudRepository<LoginGoogleRequest, LoginGoogle>(
    resourceName = "login/google",
    requestSerializer = LoginGoogleRequest.serializer(),
    responseSerializer = LoginGoogle.serializer(),
    sessionTokenRequired = false,
)