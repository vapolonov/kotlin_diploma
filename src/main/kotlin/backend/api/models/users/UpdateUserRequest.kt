package backend.api.models.users

data class UpdateUserRequest(
    val username: String? = null,
    val password: String? = null,
    val email: String? = null,
    val phoneNumber: String? = null,
)

val defaultUserWithPhone get() = UpdateUserRequest(
    username = defaultUser.username,
    password = defaultUser.password,
    email = defaultUser.email,
    phoneNumber = "+7(910)123-45-67"
)