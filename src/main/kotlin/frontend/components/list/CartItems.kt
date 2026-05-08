package frontend.components.list

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import frontend.helpers.Extensions.Companion.findByGroupId
import frontend.helpers.Extensions.Companion.toMoney
import general.browser.PageManager

class CartItems {
  private val page: Page = PageManager.get()
  private val listCartProducts: Locator get() = page.findByGroupId("cart-item")

  fun getItems(): List<CartItem> {
    return listCartProducts.all()
      .map { CartItem(
        image = it.findByGroupId("cart-item-image"),
        name = it.findByGroupId("cart-item-name").textContent(),
        price = it.findByGroupId("cart-item-unit-price").textContent().toMoney(),
        totalPrice = it.findByGroupId("cart-item-price").textContent().toMoney(),
        btnIncrement = it.findByGroupId("cart-item-increment"),
        btnDecrement = it.findByGroupId("cart-item-decrement"),
        btnRemoveItem = it.findByGroupId("cart-item-remove"),
        quantity = it.findByGroupId("cart-item-qty").textContent().toInt(),
      ) }
  }
}

data class CartItem(
  val image: Locator,
  val name: String,
  val price: Double,
  val totalPrice: Double,
  val btnIncrement: Locator,
  val btnDecrement: Locator,
  val btnRemoveItem: Locator,
  var quantity: Int,
)
