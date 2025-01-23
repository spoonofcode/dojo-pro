package repository

import core.repository.GenericCrudRepository
import model.User
import model.UserRequest

class UserRepository : GenericCrudRepository<UserRequest, User>(
    resourceName = "users",
    requestSerializer = UserRequest.serializer(),
    responseSerializer = User.serializer(),
    sessionTokenRequired = false,
)