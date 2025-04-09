package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Type

class TypeRepository : GenericCrudRepository<Type, Type>(
    resourceName = "types",
    requestSerializer = Type.serializer(),
    responseSerializer = Type.serializer(),
)