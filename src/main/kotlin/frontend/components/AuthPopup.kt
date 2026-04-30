package frontend.components

import com.microsoft.playwright.Locator
import general.browser.PageManager
import io.qameta.allure.Step

class AuthPopup : AbsBaseComponent<AuthPopup>(
  PageManager.get().locator(".dialog")
){

  private val emailInput: Locator get() = root.getByTestId("login-email").locator("input")
  private val passwordInput: Locator get() = root.getByTestId("login-password").locator("input")
  private val loginBtn: Locator get() = root.getByTestId("login-submit")
  private val txtError: Locator get() = root.getByTestId("login-error")

  @Step("Заполнить форму Login")
  fun fillLoginForm(email: String, pass: String): AuthPopup {
    emailInput.fill(email)
    passwordInput.fill(pass)
    return this
  }

  @Step("Нажать на кнопку Login")
  fun submitLogin(): AuthPopup {
    loginBtn.click()
    return this
  }

  @Step("Получить текст ошибки")
  fun getErrorText(): String {
    txtError.isVisible
    return txtError.textContent()
  }
}