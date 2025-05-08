package com.spoonofcode.dojopro.core.data.repository

interface ProfileRepositoryInterface {
    fun testBartek (): String
}

//class ProfileRepository : GenericCrudRepository<ProfileRequest, Profile>(
//    resourceName = "profile",
//    requestSerializer = ProfileRequest.serializer(),
//    responseSerializer = Profile.serializer(),
//) {
//    fun testBartek(): String {
//        return "Sdas "
//    }
//}

class ProfileRepository : ProfileRepositoryInterface {
    override fun testBartek(): String {
        return "Sdas "
    }
}