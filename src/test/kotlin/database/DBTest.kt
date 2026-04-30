package database

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.users.defaultUser
import backend.controllers.Controllers
import backend.helpers.Utils.Companion.randomEmailPrefix
import backend.helpers.Utils.Companion.randomUsername
import frontend.pages.MainPage
import general.jupiter.annotations.UITest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@UITest
class DBTest : Controllers() {

  @Test
  fun deleteOrderTest() {
    JDBCHelper().deleteOrderById(91)
  }

  @Test
  @DisplayName("Проверка создания пользователя в базе данных API-UI-DB")
  fun userShouldCreatedInDatabase() {
    val apiUser = users.createUser( defaultUser).getAsObject()

    MainPage().open()
      .openLoginForm()
      .authPopup()
      .fillLoginForm(apiUser.email, "random")
      .submitLogin()
    MainPage().header().checkAvatar()

    val dbUser = JDBCHelper().getUserByEmail(apiUser.email)

    dbUser?.id shouldBe apiUser.id
    dbUser?.username shouldBe apiUser.username

  }

  @Test
  @DisplayName("Проверка создания пользователя в базе данных UI-API-DB")
  fun userShouldCreatedInDatabase2() {
    val email = "${randomEmailPrefix()}@autotest.com"
    val username = randomUsername()
    MainPage().open()
      .openCreateAccountForm()
      .createUserPopup()
      .fillCreateAccountForm(
        username,
        email,
        "random"
      )
      .submitCreateUser()

    MainPage().header().checkAvatar()

    val dbUser = JDBCHelper().getUserByEmail(email)
    dbUser?.username shouldBe username

    val apiUser = users.getAllUsers().getAsObject()
//    val apiUser = users.getUserById(id = dbUser?.id ?: 1).getAsObject()

    apiUser.firstOrNull { it.email == email }?.username shouldBe dbUser?.username
  }
}