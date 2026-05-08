package frontend.pages

import com.microsoft.playwright.Locator
import frontend.components.Header
import frontend.components.list.ProductItem
import frontend.components.list.ProductsItems
import frontend.helpers.Extensions.Companion.findByGroupId
import frontend.helpers.Extensions.Companion.findByTestId
import general.base.BasePage
import io.qameta.allure.Step

class ProductsPage : BasePage<ProductsPage>() {
  override fun endpoint() = "/products"
  private val txtTitle: Locator get() = page.findByTestId("products-title")
  private val listItems: Locator get() = page.findByGroupId("product-card")

  @Step("Получить название страницы продуктов")
  fun getTitle(): String {
    return txtTitle.textContent()
  }

  @Step("Получить список продуктов на странице")
  fun getProducts(): Locator {
    return listItems
  }

  @Step("Получить список продуктов на станице Products в виде объектов")
  fun getProductsItems(): List<ProductItem> {
    return ProductsItems().getItems()
  }

  @Step("Перейти к компоненту Header")
  fun header(): Header {
    return Header()
  }
}