package backend.api.models.auth

data class LoginRequest(
  val email: String?,
  val password: String?
)

val defaultAdmin get() = LoginRequest(
    email = "admin@test.com",
    password = "QWE123qwe",
)
