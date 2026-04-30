package frontend.pages

import com.microsoft.playwright.Locator
import frontend.components.AuthPopup
import frontend.components.CreateUserPopup
import frontend.components.Header
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import frontend.helpers.Extensions.Companion.shouldHaveSizeGreaterThan
import frontend.helpers.Wrappers.Companion.getByTestGroupId
import general.base.AbsBasePage
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.qameta.allure.Step

class MainPage : AbsBasePage<MainPage>() {

  override fun endpoint() = "/"
  private val mainTitle: Locator = page.getByTestId("main-image-text")
  private val joinBtn: Locator get() = page.getByTestId("nav-link-auth")
  private val loginLink: Locator get() = page.getByTestId("create-login")
  private val listPopularProducts: Locator get() = page.locator(getByTestGroupId("product-card"))

  fun checkPageLoaded(): MainPage {
    waitForVisible(mainTitle)
    return this
  }

  @Step("Получить список популярных товаров")
  fun getPopularProducts(): List<ProductItem> {
    listPopularProducts.count() shouldBeGreaterThan 0
    return ProductsItems().getItems()
  }

  @Step("Проверить количество популярных товаров на главной")
  fun checkNumberOfPopularProducts(): MainPage {
    listPopularProducts.count() shouldBeGreaterThan 0
    return this
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