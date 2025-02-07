package core.data.repository

import core.base.repository.GenericCrudRepository
import core.model.Level

class LevelRepository : GenericCrudRepository<Level, Level>(
    resourceName = "levels",
    requestSerializer = Level.serializer(),
    responseSerializer = Level.serializer(),
)