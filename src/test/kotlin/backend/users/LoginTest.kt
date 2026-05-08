package backend.users

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.controllers.Controllers
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("API")
@Story("Тесты на авторизацию по API")
class LoginTest : Controllers() {

  @Test
  @DisplayName("Вход в систему с валидными учетными данными должен возвращать access token и refresh token")
  fun testLoginWithValidCredentials() {
    val response = auth.login("admin@test.com", "QWE123qwe").getAsObject()

    response.accessToken.length.shouldBeGreaterThan(10)
    response.refreshToken.length.shouldBeGreaterThan(10)
  }

  @Test
  @DisplayName("Вход в систему с невалидными учетными данными должен возвращать ошибку")
  fun testLoginWithInvalidCredentials() {
    val response = auth.login("admin", "123").getErrorAsObject<ErrorResponse>()

    response.code shouldBe 400
    response.reason shouldBe "Invalid email or password"
  }
}