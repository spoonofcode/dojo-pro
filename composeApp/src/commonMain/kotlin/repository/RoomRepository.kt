package repository

import core.repository.GenericCrudRepository
import model.Room

class RoomRepository : GenericCrudRepository<Room, Room>(
    resourceName = "rooms",
    requestSerializer = Room.serializer(),
    responseSerializer = Room.serializer(),
)