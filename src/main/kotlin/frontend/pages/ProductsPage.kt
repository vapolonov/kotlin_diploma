package frontend.pages

import com.microsoft.playwright.Locator
import frontend.components.Header
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import frontend.helpers.Extensions.Companion.shouldBeVisible
import frontend.helpers.Wrappers.Companion.getByTestGroupId
import general.base.AbsBasePage
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.qameta.allure.Step

class ProductsPage : AbsBasePage<ProductsPage>() {
  override fun endpoint() = "/products"
  private val txtTitle: Locator get() = page.getByTestId("products-title")
  private val listItems: Locator get() = page.locator(getByTestGroupId("product-card"))
  private val listProducts get() = ProductsItems().getItems()

  @Step("Получить название страницы продуктов")
  fun getTitle(): String {
    txtTitle.shouldBeVisible()
    return txtTitle.textContent()
  }

  @Step("Получить список продуктов на странице")
  fun getProducts(): Locator {
    listItems.first().shouldBeVisible()
    return listItems
  }

  @Step("Получить список продуктов на станице Products в виде объектов")
  fun getProductsItems(): List<ProductItem> {
    listItems.count() shouldBeGreaterThan 0
    return ProductsItems().getItems()
  }

  @Step("Получить список товаров")
  fun getProductsAsObjects(): List<ProductItem> {
    listItems.first().shouldBeVisible()
    return listProducts
  }

  @Step("Перейти к компоненту Header")
  fun header(): Header {
    return Header()
  }
}