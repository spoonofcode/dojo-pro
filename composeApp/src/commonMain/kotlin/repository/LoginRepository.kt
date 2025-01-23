package repository

import core.repository.GenericCrudRepository
import model.Login
import model.LoginRequest
import model.User
import model.UserRequest

class LoginRepository : GenericCrudRepository<LoginRequest, Login>(
    resourceName = "login",
    requestSerializer = LoginRequest.serializer(),
    responseSerializer = Login.serializer(),
    sessionTokenRequired = false,
)