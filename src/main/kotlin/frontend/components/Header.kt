package frontend.components

import com.microsoft.playwright.Locator
import frontend.helpers.Extensions.Companion.findByGroupId
import frontend.pages.ProductsPage
import general.browser.PageManager
import io.qameta.allure.Step

class Header : BaseComponent<Header>(
  PageManager.get().locator(".header")
){

  private val linksHeader: Locator get() = root.findByGroupId("nav-link")
  private val avatar: Locator get() = root.locator(".avatar")

  @Step("Получить список ссылок в шапке")
  fun clickLink(linkName: String): Header {
    linksHeader
      .filter(Locator.FilterOptions().setHasText(linkName))
      .first()
      .click()
    return this
  }

  @Step("Получить список ссылок в шапке")
  fun getLinks(): List<String> {
    return linksHeader.allTextContents()
  }

  @Step("Проверить видимость аватарки")
  fun checkAvatar(): Boolean {
    return avatar.isVisible
  }

  @Step("Перейти на страницу Products")
  fun toProducts(): ProductsPage = ProductsPage()

}