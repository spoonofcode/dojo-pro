package repository

import core.repository.GenericCrudRepository
import model.Room

class RoomRepository : GenericCrudRepository<Room, Room>(
    resourceName = "rooms",
    requestSerializer = Room.serializer(), // Pass serializer for RQ
    responseSerializer = Room.serializer() // Pass serializer for RS
)