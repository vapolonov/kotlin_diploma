package frontend.components.list

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import frontend.helpers.Extensions.Companion.toMoney
import frontend.helpers.Wrappers.Companion.getByTestGroupId
import general.browser.PageManager

class ProductsItems {
  private val page: Page = PageManager.get()
  private val listProducts get() = page.locator(getByTestGroupId("product-card"))

  fun getItems(): List<ProductItem> {
    return listProducts.all()
      .map { ProductItem(
        image = it.locator(getByTestGroupId("product-card-image")),
        name = it.locator(getByTestGroupId("product-card-name")).textContent(),
        description = it.locator(getByTestGroupId("product-card-description")).textContent(),
        price = it.locator(getByTestGroupId("product-card-price")).textContent().toMoney(),
        btnIncrement = it.locator(getByTestGroupId("product-card-increment")),
        btnDecrement = it.locator(getByTestGroupId("product-card-decrement")),
        quantity = it.locator(getByTestGroupId("product-card-qty")).textContent().toInt(),
      ) }
  }
}

data class ProductItem(
  val image: Locator,
  val name: String,
  val description: String,
  val price: Double,
  val btnIncrement: Locator,
  val btnDecrement: Locator,
  var quantity: Int,
)
