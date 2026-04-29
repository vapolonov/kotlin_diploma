package frontend.components

import com.microsoft.playwright.Locator
import general.browser.PageManager
import io.kotest.matchers.shouldBe
import io.qameta.allure.Step

class CreateUserPopup : AbsBaseComponent<CreateUserPopup>(PageManager.get().locator(".dialog"))
{
  private val usernameInput: Locator get() = root.getByTestId("create-username").locator("input")
  private val emailInput: Locator get() = root.getByTestId("create-email").locator("input")
  private val passwordInput: Locator get() = root.getByTestId("create-password").locator("input")
  private val createUserBtn: Locator get() = root.getByTestId("create-submit").locator("button")
  private val errorMsg: Locator get() = root.getByTestId("create-error")

  @Step("Заполнить форму Create Account")
  fun fillCreateAccountForm(username: String, email: String, pass: String): CreateUserPopup = apply {
    usernameInput.fill(username)
    emailInput.fill(email)
    passwordInput.fill(pass)
  }

  @Step("Нажать на кнопку Create User")
  fun submitCreateUser(): CreateUserPopup  = apply {
    createUserBtn.click()
  }

  @Step("Нажать на кнопку Create User")
  fun getErrorText(): String  {
    return errorMsg.textContent()
  }
}
