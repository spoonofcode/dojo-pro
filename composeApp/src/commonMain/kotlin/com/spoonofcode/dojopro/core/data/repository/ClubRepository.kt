package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Club

class ClubRepository : GenericCrudRepository<Club, Club>(
    resourceName = "clubs",
    requestSerializer = Club.serializer(),
    responseSerializer = Club.serializer(),
)