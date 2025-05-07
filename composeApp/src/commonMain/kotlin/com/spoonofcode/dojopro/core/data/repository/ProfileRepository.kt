package com.spoonofcode.dojopro.core.data.repository

import com.spoonofcode.dojopro.core.data.base.GenericCrudRepository
import com.spoonofcode.dojopro.core.model.Profile
import com.spoonofcode.dojopro.core.model.ProfileRequest
import io.mockative.Mockable

@Mockable
class ProfileRepository : GenericCrudRepository<ProfileRequest, Profile>(
    resourceName = "profile",
    requestSerializer = ProfileRequest.serializer(),
    responseSerializer = Profile.serializer(),
) {
    fun testBartek(): String {
        return "Sdas "
    }
}