package repository

import core.repository.GenericCrudRepository
import model.Coach

class CoachRepository : GenericCrudRepository<Coach, Coach>(
    resourceName = "coaches",
    requestSerializer = Coach.serializer(),
    responseSerializer = Coach.serializer(),
)