package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Type
import com.spoonofcode.dojopro.core.test.OpenForMokkery

@OpenForMokkery
class TypeRepository : GenericCrudRepository<Type, Type>(
    resourceName = "types",
    requestSerializer = Type.serializer(),
    responseSerializer = Type.serializer(),
)