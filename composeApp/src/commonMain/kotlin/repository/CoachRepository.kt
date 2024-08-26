package repository

import core.repository.GenericCrudRepository
import model.Coach

class CoachRepository : GenericCrudRepository<Coach, Coach>(
    resourceName = "coaches",
    requestSerializer = Coach.serializer(), // Pass serializer for RQ
    responseSerializer = Coach.serializer() // Pass serializer for RS
)