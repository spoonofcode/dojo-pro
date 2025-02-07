package core.data.repository

import core.base.repository.GenericCrudRepository
import core.model.Register
import core.model.RegisterRequest

class RegisterRepository : GenericCrudRepository<RegisterRequest, Register>(
    resourceName = "register",
    requestSerializer = RegisterRequest.serializer(),
    responseSerializer = Register.serializer(),
    sessionTokenRequired = false,
)