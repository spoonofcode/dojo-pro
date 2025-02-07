package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.base.repository.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Coach

class CoachRepository : GenericCrudRepository<Coach, Coach>(
    resourceName = "coaches",
    requestSerializer = Coach.serializer(),
    responseSerializer = Coach.serializer(),
)