package backend.api.models.users

data class UserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val phoneNumber: String,
    val createdAt: Long
)