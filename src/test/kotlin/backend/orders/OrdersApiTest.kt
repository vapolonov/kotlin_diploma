package backend.orders

import backend.controllers.Controllers
import backend.helpers.OrdersHelper
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("API")
class OrdersApiTest : Controllers() {

  @Test
  fun testAllOrders() {
    OrdersHelper().createOrder(productIds=listOf(219, 217) )
    val allOrders = orders.getAllOrders()
    allOrders.first().orderStatus shouldBe "PENDING"
    allOrders.forEach { it.products.size shouldBeGreaterThan 0 }
  }

  @Test
  fun testCreateOrderWithUserNull() {
    val orders = OrdersHelper().createOrder(userId = null)
    orders.userId shouldBe null
  }
}