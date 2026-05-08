package backend.users

import backend.api.extensions.Extensions.Companion.checkIsSuccessful
import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.UpdateUserRequest
import backend.api.models.users.defaultUser
import backend.api.models.users.defaultUserWithPhone
import backend.controllers.Controllers
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.qameta.allure.Story
import net.datafaker.Faker
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("API")
@Story("Тесты на создание пользователя по API")
class UsersTest : Controllers() {
  private val testData = Faker()

  @Test
  @DisplayName("Создание и удаление пользователя")
  fun testCreateAndDeleteUser() {
    val username = testData.credentials().username()
    val email = testData.internet().emailAddress()
    val password = testData.credentials().password()

    val response = users.createUser(CreateUserRequest(username, email, password)).getAsObject()

    response.username shouldBe username
    response.email shouldBe email

    val delete = users.deleteUserById(id = response.id)

    delete.code() shouldBe 200
  }

  @Test
  @DisplayName("Создание нового пользователя с пустым паролем")
  fun testCreateUserWithoutPassword() {
    val username = testData.credentials().username()
    val email = testData.internet().emailAddress()

    runCatching {
      users.createUser(CreateUserRequest(username, email, " ")).getErrorAsObject<ErrorResponse>()
    }.isFailure.shouldBeTrue()
  }

  @Test
  @DisplayName("Создание пользователя с валидными данными")
  fun testCreateUserWithValidData() {
    val user = users.createUser(defaultUser).getAsObject()
    val expectedUser = users.getUserById(id = user.id)

    expectedUser shouldBeEqualToComparingFields user
  }

  @Test
  @DisplayName("Обновление данных пользователя")
  fun testUpdateUser() {
    val user = users.createUser(defaultUser).getAsObject()
    val userWithPhone = users.updateUserById(
      id = user.id, body = defaultUserWithPhone
    ).getAsObject()
    val expectedUser = users.getUserById(id = userWithPhone.id)

    expectedUser shouldBeEqualToComparingFields userWithPhone
  }

  @Test
  @DisplayName("Обновление всех данных пользователя")
  fun testUpdateFullUser() {
    val user = users.createUser(defaultUser).getAsObject()
    val updateRequest = UpdateUserRequest(
      username = "updatedUsername",
      email = testData.internet().emailAddress(),
      password = "updatedPassword",
      phoneNumber = "1234567890"
    )
    val updUser = users.updateUserById(id = user.id, body = updateRequest).getAsObject()
    val login = auth.login(email = updateRequest.email, password = updateRequest.password).getAsObject()
    login.accessToken.length shouldBeGreaterThan 10
    updUser.phoneNumber shouldBe updateRequest.phoneNumber
    updUser.username shouldBe updateRequest.username
    updUser.email shouldBe updateRequest.email
  }

  @Test
  @DisplayName("Частичное обновление данных пользователя")
  fun testUpdatePartialUser() {
    val user = users.createUser(defaultUser).getAsObject()

    val updateRequest = UpdateUserRequest(password = "updatedPassword")
    users.updateUserById(id = user.id, body = updateRequest).checkIsSuccessful()

    val login = auth.login(email = user.email, password = updateRequest.password).getAsObject()

    login.accessToken.length shouldBeGreaterThan 10
  }

  @Test
  @DisplayName("Получить требуемого пользователя из всех пользователей")
  fun testGetRequestedUsers() {
    val user = users.createUser(defaultUser).getAsObject()
    val allUsers = users.getAllUsers().getAsObject()
    allUsers shouldContain user
  }

}