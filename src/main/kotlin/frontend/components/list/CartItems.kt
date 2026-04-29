package frontend.components.list

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import frontend.helpers.Extensions.Companion.toMoney
import frontend.helpers.Wrappers.Companion.getByTestGroupId
import general.browser.PageManager

class CartItems {
  private val page: Page = PageManager.get()
  private val listCartProducts: Locator get() = page.locator(getByTestGroupId("cart-item"))

  fun getItems(): List<CartItem> {
    return listCartProducts.all()
      .map { CartItem(
        image = it.locator(getByTestGroupId("cart-item-image")),
        name = it.locator(getByTestGroupId("cart-item-name")).textContent(),
        price = it.locator(getByTestGroupId("cart-item-unit-price")).textContent().toMoney(),
        totalPrice = it.locator(getByTestGroupId("cart-item-price")).textContent().toMoney(),
        btnIncrement = it.locator(getByTestGroupId("cart-item-increment")),
        btnDecrement = it.locator(getByTestGroupId("cart-item-decrement")),
        btnRemoveItem = it.locator(getByTestGroupId("cart-item-remove")),
        quantity = it.locator(getByTestGroupId("cart-item-qty")).textContent().toInt(),
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
