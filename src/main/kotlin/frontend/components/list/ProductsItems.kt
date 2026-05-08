package frontend.components.list

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import frontend.helpers.Extensions.Companion.findByGroupId
import frontend.helpers.Extensions.Companion.toMoney
import general.browser.PageManager

class ProductsItems {
  private val page: Page = PageManager.get()
  private val listProducts get() = page.findByGroupId("product-card")

  fun getItems(): List<ProductItem> {
    return listProducts.all()
      .map { ProductItem(
        image = it.findByGroupId("product-card-image"),
        name = it.findByGroupId("product-card-name").textContent(),
        description = it.findByGroupId("product-card-description").textContent(),
        price = it.findByGroupId("product-card-price").textContent().toMoney(),
        btnIncrement = it.findByGroupId("product-card-increment"),
        btnDecrement = it.findByGroupId("product-card-decrement"),
        quantity = it.findByGroupId("product-card-qty").textContent().toInt(),
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
