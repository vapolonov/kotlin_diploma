package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.models.auth.LoginRequest
import backend.api.models.auth.LoginResponse
import io.qameta.allure.Step
import retrofit2.Response

class AuthController : Endpoints() {

  @Step("Login with email: {email} and password {password}")
  fun login(email: String?, password: String?): Response<LoginResponse> {
    return authApi.postLogin(body = LoginRequest(email = email, password = password)).execute()
  }
}