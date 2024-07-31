package model

data class Coach(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val fullName: String = "$firstName $lastName"
)