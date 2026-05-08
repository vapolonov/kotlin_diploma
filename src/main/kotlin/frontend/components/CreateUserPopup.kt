package frontend.components

import com.microsoft.playwright.Locator
import frontend.helpers.Extensions.Companion.findByTestId
import general.browser.PageManager
import io.qameta.allure.Step

class CreateUserPopup : BaseComponent<CreateUserPopup>(PageManager.get().locator(".dialog"))
{
  private val usernameInput: Locator get() = root.findByTestId("create-username").locator("input")
  private val emailInput: Locator get() = root.findByTestId("create-email").locator("input")
  private val passwordInput: Locator get() = root.findByTestId("create-password").locator("input")
  private val createUserBtn: Locator get() = root.findByTestId("create-submit").locator("button")
  private val errorMsg: Locator get() = root.findByTestId("create-error")

  @Step("Заполнить форму Create Account")
  fun fillCreateAccountForm(username: String, email: String, pass: String): CreateUserPopup {
    usernameInput.fill(username)
    emailInput.fill(email)
    passwordInput.fill(pass)
    return this
  }

  @Step("Нажать на кнопку Create User")
  fun submitCreateUser(): CreateUserPopup {
    createUserBtn.click()
    return this
  }

  @Step("Нажать на кнопку Create User")
  fun getErrorText(): String  {
    return errorMsg.textContent()
  }
}
