package repository

import core.repository.GenericCrudRepository
import model.SportEvent
import model.SportEventRequest

class SportEventRepository : GenericCrudRepository<SportEventRequest, SportEvent>(
    resourceName = "sportEvents",
    requestSerializer = SportEventRequest.serializer(), // Pass serializer for RQ
    responseSerializer = SportEvent.serializer() // Pass serializer for RS
)