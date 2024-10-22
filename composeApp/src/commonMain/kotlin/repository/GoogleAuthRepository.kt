package repository

import core.repository.GenericCrudRepository
import model.GoogleAuthToken
import model.GoogleAuthTokenRequest

class GoogleAuthRepository : GenericCrudRepository<GoogleAuthTokenRequest, GoogleAuthToken>(
    resourceName = "google-auth",
    requestSerializer = GoogleAuthTokenRequest.serializer(), // Pass serializer for RQ
    responseSerializer = GoogleAuthToken.serializer() // Pass serializer for RS
)