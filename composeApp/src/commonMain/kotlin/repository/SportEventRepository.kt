package repository

import core.repository.GenericCrudRepository
import model.SportEvent
import model.SportEventRequest

class SportEventRepository : GenericCrudRepository<SportEventRequest, SportEvent>(
    resourceName = "sportEvents",
    requestSerializer = SportEventRequest.serializer(),
    responseSerializer = SportEvent.serializer(),
)