package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.model.ProfileRequest
import com.spoonofcode.dojopro.core.test.OpenForMokkery

@OpenForMokkery
class ProfileRepository : GenericCrudRepository<ProfileRequest, Profile>(
    resourceName = "profile",
    requestSerializer = ProfileRequest.serializer(),
    responseSerializer = Profile.serializer(),
)