package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Room

class RoomRepository : GenericCrudRepository<Room, Room>(
    resourceName = "rooms",
    requestSerializer = Room.serializer(),
    responseSerializer = Room.serializer(),
)