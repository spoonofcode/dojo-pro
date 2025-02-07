package core.data.repository

import core.base.repository.GenericCrudRepository
import core.model.User
import core.model.UserRequest

class UserRepository : GenericCrudRepository<UserRequest, User>(
    resourceName = "users",
    requestSerializer = UserRequest.serializer(),
    responseSerializer = User.serializer(),
    sessionTokenRequired = false,
)