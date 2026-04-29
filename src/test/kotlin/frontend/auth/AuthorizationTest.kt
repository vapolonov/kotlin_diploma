package frontend.auth

import backend.helpers.Utils.Companion.randomEmailPrefix
import frontend.pages.MainPage
import general.jupiter.annotations.UITest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@UITest
@Tag("UI")
class AuthorizationTest {

  @ParameterizedTest
  @CsvSource(
    "admin, 123,Invalid email or password",
    "admin,'',Please enter email and password",
    "'', 123,Please enter email and password",
    "'','',Please enter email and password"
  )
  @DisplayName("Проверить авторизацию с НЕ корректными данными")
  fun testUnsuccessfulLogin(email: String, password: String, message: String) {
    val error = MainPage().open()
      .openLoginForm()
      .authPopup()
      .fillLoginForm(email, password)
      .submitLogin()
      .getErrorText()
    error shouldBe message
  }

  @Test
  @DisplayName("Проверить авторизацию с корректными данными")
  fun testLogin() {
    MainPage().open()
      .openLoginForm()
      .authPopup()
      .fillLoginForm("admin@test.com", "QWE123qwe")
      .submitLogin()
    MainPage().header().checkAvatar()
  }

  @Test
  @DisplayName("Создание нового пользователя c корректными данными")
  fun testCreateUser() {
    MainPage().open()
      .openCreateAccountForm()
      .createUserPopup()
      .fillCreateAccountForm(
        "random",
        "${randomEmailPrefix()}@autotest.com",
        "random"
      )
      .submitCreateUser()

    MainPage().header().checkAvatar()
  }

  @Test
  @DisplayName("Создание нового пользователя c некорректными данными")
  fun testCreateUserWrongData() {
    val errorMsg = MainPage().open()
      .openCreateAccountForm()
      .createUserPopup()
      .fillCreateAccountForm(
        "",
        "${randomEmailPrefix()}@autotest.com",
        "random"
      )
      .submitCreateUser()
      .getErrorText()

      errorMsg shouldBe "Please enter username, email and password"
  }

  @Test
  @DisplayName("Создание существующего пользователя")
  fun testCreateExistingUser() {
    val errorMsg = MainPage().open()
      .openCreateAccountForm()
      .createUserPopup()
      .fillCreateAccountForm(
        "admin",
        "admin@test.com",
        "random"
      )
      .submitCreateUser()
      .getErrorText()

      errorMsg shouldBe "Something went wrong. Please verify request."
  }
}