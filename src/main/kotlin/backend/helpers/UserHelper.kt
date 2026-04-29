package backend.helpers

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.UserResponse
import backend.controllers.Controllers
import backend.helpers.Utils.Companion.randomEmailPrefix

class UserHelper : Controllers() {

  fun createUser(password: String = "random"): UserResponse {
    return users.createUser(
      body = CreateUserRequest(
        username = "random",
        email = "${randomEmailPrefix()}@autotest.com",
        password,
      )
    ).getAsObject()
  }
}