package backend.api.models.users

import backend.helpers.Utils.Companion.randomEmailPrefix

data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String,
)

val defaultUser get() = CreateUserRequest(
    username = "random",
    password = "random",
    email = "${randomEmailPrefix()}@autotest.com"
)