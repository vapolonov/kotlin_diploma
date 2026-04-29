package backend.helpers

import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.OrdersResponse
import backend.controllers.Controllers
import io.qameta.allure.Step

class OrdersHelper : Controllers() {

  @Step("Создать заказ")
  fun createOrder(
    userId: Int? = null,
    productIds: List<Int> = listOf(219) // vararg productIds: Int использование create(userId, 1, 2, 3)
  ): OrdersResponse {
    return orders.createOrder(
      body = CreateOrderRequest(
        userId = userId,
        products = productIds.map { CreateOrderRequest.Product(it) }
      )
    )
  }
}