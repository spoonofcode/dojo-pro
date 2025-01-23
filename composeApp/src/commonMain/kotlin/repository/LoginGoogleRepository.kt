package repository

import core.repository.GenericCrudRepository
import model.LoginGoogle
import model.LoginGoogleRequest

class LoginGoogleRepository : GenericCrudRepository<LoginGoogleRequest, LoginGoogle>(
    resourceName = "login/google",
    requestSerializer = LoginGoogleRequest.serializer(),
    responseSerializer = LoginGoogle.serializer(),
    sessionTokenRequired = false,
)