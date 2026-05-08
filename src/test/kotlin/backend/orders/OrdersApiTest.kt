package backend.orders

import backend.controllers.Controllers
import backend.helpers.OrdersHelper
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("API")
@Story("Тесты на создание заказа по API")
class OrdersApiTest : Controllers() {

  @Test
  @DisplayName("Создание заказа с несколькими товарами")
  fun testAllOrders() {
    OrdersHelper().createOrder(productIds=listOf(219, 217) )
    val allOrders = orders.getAllOrders()
    allOrders.first().orderStatus shouldBe "PENDING"
    allOrders.forEach { it.products.size shouldBeGreaterThan 0 }
  }

  @Test
  @DisplayName("Создание заказа неавторизованным пользователем")
  fun testCreateOrderWithUserNull() {
    val orders = OrdersHelper().createOrder(userId = null)
    orders.userId shouldBe null
  }
}