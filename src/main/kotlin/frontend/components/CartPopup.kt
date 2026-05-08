package frontend.components

import com.microsoft.playwright.Locator
import frontend.components.list.CartItem
import frontend.components.list.CartItems
import frontend.helpers.Extensions.Companion.findByTestId
import general.browser.PageManager
import io.qameta.allure.Step

class CartPopup : BaseComponent<CartPopup>(
  PageManager.get().locator(".cart-popup")
) {

  private val checkout: Locator get() = root.findByTestId("cart-checkout").locator("#button")

  @Step("Получить список товаров в корзине")
  fun getCartProducts(): List<CartItem> {
    return CartItems().getItems()
  }

  @Step("Создать заказ")
  fun createOrder(): CartPopup {
    checkout.click()
    return this
  }
}