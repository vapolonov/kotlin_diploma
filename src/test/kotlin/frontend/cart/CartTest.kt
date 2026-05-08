package frontend.cart

import frontend.components.CartPopup
import frontend.components.list.ProductItem
import frontend.pages.MainPage
import general.jupiter.annotations.UITest
import io.kotest.matchers.equality.shouldBeEqualToDifferentTypeIgnoringFields
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@UITest
@Tag("UI")
@Story("Тесты на корзину товаров")
class CartTest {

  @Test
  @DisplayName("Проверка продуктов в корзине")
  fun testProductsInCart() {
    val firstPopularProduct = MainPage().open()
      .getPopularProducts()
      .first()
    firstPopularProduct.btnIncrement.click()

    MainPage().header().clickLink("Cart")
    val firstCartProduct = CartPopup().getCartProducts().first()

    firstPopularProduct.apply { quantity = 1 }
      .shouldBeEqualToDifferentTypeIgnoringFields(
      firstCartProduct,
        ProductItem::description,
      ProductItem::btnIncrement,
      ProductItem::btnDecrement,
      ProductItem::image,
    )
  }
}