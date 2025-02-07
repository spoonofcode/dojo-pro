package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Level

class LevelRepository : GenericCrudRepository<Level, Level>(
    resourceName = "levels",
    requestSerializer = Level.serializer(),
    responseSerializer = Level.serializer(),
)