package repository

import core.repository.GenericCrudRepository
import model.Level

class LevelRepository : GenericCrudRepository<Level, Level>(
    resourceName = "levels",
    requestSerializer = Level.serializer(), // Pass serializer for RQ
    responseSerializer = Level.serializer() // Pass serializer for RS
)