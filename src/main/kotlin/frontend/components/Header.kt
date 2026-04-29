package frontend.components

import com.microsoft.playwright.Locator
import frontend.elements.Button
import frontend.helpers.Extensions.Companion.shouldBeVisible
import frontend.helpers.Wrappers.Companion.getByTestGroupId
import frontend.pages.ProductsPage
import general.browser.PageManager
import io.qameta.allure.Step

class Header : AbsBaseComponent<Header>(
  PageManager.get().locator(".header")
){

  private val homeBtn = Button(root.locator("a[href='/']"))
  private val coursesBtn = Button(root.locator("a[href='/courses']"))

  private val linksHeader: Locator get() = root.locator(getByTestGroupId("nav-link"))
  private val avatar: Locator get() = root.locator(".avatar")

  fun clickCourses(): Header = apply {
    coursesBtn.click()
  }

  @Step("Получить список ссылок в шапке")
  fun clickLink(linkName: String): Header = apply {
    linksHeader
      .filter(Locator.FilterOptions().setHasText(linkName))
      .first()
      .click()
  }

  @Step("Получить список ссылок в шапке")
  fun getLinks(): List<String> {
    return linksHeader.allTextContents()
  }

  @Step("Проверить видимость аватарки")
  fun checkAvatar(): Header = apply {
    avatar.shouldBeVisible()
  }

  @Step("Перейти на страницу Products")
  fun toProducts(): ProductsPage = ProductsPage()

}