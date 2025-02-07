package core.data.repository

import core.base.repository.GenericCrudRepository
import core.model.SportEvent
import core.model.SportEventRequest

class SportEventRepository : GenericCrudRepository<SportEventRequest, SportEvent>(
    resourceName = "sportEvents",
    requestSerializer = SportEventRequest.serializer(),
    responseSerializer = SportEvent.serializer(),
)