package core.data.repository

import core.base.repository.GenericCrudRepository
import core.model.LoginGoogle
import core.model.LoginGoogleRequest

class LoginGoogleRepository : GenericCrudRepository<LoginGoogleRequest, LoginGoogle>(
    resourceName = "login/google",
    requestSerializer = LoginGoogleRequest.serializer(),
    responseSerializer = LoginGoogle.serializer(),
    sessionTokenRequired = false,
)