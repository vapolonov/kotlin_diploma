package frontend.pages

import com.microsoft.playwright.Locator
import frontend.components.AuthPopup
import frontend.components.CreateUserPopup
import frontend.components.Header
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import frontend.helpers.Extensions.Companion.findByTestId
import general.base.BasePage
import io.qameta.allure.Step

class MainPage : BasePage<MainPage>() {

  override fun endpoint() = "/"
  private val mainTitle: Locator = page.findByTestId("main-image-text")
  private val joinBtn: Locator get() = page.findByTestId("nav-link-auth")
  private val loginLink: Locator get() = page.findByTestId("create-login")

  fun checkPageLoaded(): MainPage {
    waitForVisible(mainTitle)
    return this
  }

  @Step("Получить список популярных товаров")
  fun getPopularProducts(): List<ProductItem> {
    return ProductsItems().getItems()
  }

  @Step("Перейти к компоненту Header")
  fun header(): Header {
    return Header()
  }

  @Step("Открыть форму Login")
  fun openLoginForm(): MainPage {
    joinBtn.click()
    loginLink.click()
    return this
  }

  @Step("Открыть форму Create Account")
  fun openCreateAccountForm(): MainPage {
    joinBtn.click()
    return this
  }

  @Step("Перейти к компоненту AuthPopup")
  fun authPopup(): AuthPopup = AuthPopup()

  @Step("Перейти к компоненту Create User")
  fun createUserPopup(): CreateUserPopup = CreateUserPopup()

}