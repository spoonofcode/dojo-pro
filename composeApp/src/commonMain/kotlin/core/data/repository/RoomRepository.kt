package core.data.repository

import core.base.repository.GenericCrudRepository
import core.model.Room

class RoomRepository : GenericCrudRepository<Room, Room>(
    resourceName = "rooms",
    requestSerializer = Room.serializer(),
    responseSerializer = Room.serializer(),
)