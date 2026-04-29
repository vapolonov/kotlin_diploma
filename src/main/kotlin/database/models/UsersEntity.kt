package database.models

data class UsersEntity(
  var id: Int,
  var username: String,
  var email: String,
  var password: String,
)
