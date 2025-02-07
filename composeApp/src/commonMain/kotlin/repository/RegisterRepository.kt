package repository

import core.repository.GenericCrudRepository
import model.Login
import model.LoginRequest
import model.Register
import model.RegisterRequest
import model.User
import model.UserRequest

class RegisterRepository : GenericCrudRepository<RegisterRequest, Register>(
    resourceName = "register",
    requestSerializer = RegisterRequest.serializer(),
    responseSerializer = Register.serializer(),
    sessionTokenRequired = false,
)