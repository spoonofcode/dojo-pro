package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.SportEvent
import com.spoonofcode.dojopro.core.model.SportEventRequest

class SportEventRepository : GenericCrudRepository<SportEventRequest, SportEvent>(
    resourceName = "sportEvents",
    requestSerializer = SportEventRequest.serializer(),
    responseSerializer = SportEvent.serializer(),
)