package core.data.repository

import core.base.repository.GenericCrudRepository
import core.model.Coach

class CoachRepository : GenericCrudRepository<Coach, Coach>(
    resourceName = "coaches",
    requestSerializer = Coach.serializer(),
    responseSerializer = Coach.serializer(),
)