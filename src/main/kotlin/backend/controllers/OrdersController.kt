package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.OrdersResponse
import backend.api.models.orders.UpdateOrderStatusRequest
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import io.qameta.allure.Step
import retrofit2.Response

class OrdersController : Endpoints() {
  private val authHelper = AuthorizationHelper()

  @Step("Получить все заказы по API")
  fun getAllOrders(
    token: String = authHelper.getAdminToken(),
    offset: Int = 0,
    limit: Int = 100
  ): List<OrdersResponse> {
    return ordersApi.getOrders(token, offset, limit).execute().getAsObject()
  }

  @Step("Получить заказ по ID")
  fun getOrderById(
    token: String = authHelper.getAdminToken(),
    id: Int
  ): OrdersResponse {
    return ordersApi.getOrderById(token, id).execute().getAsObject()
  }

  @Step("Получить все заказы пользователя по userId")
  fun getOrderByUserId(
    token: String = authHelper.getAdminToken(),
    id: Int
  ): List<OrdersResponse> {
    return ordersApi.getOrderByUserId(token, id).execute().getAsObject()
  }

  @Step("Изменить статус заказа")
  fun updateOrderById(
    token: String = authHelper.getAdminToken(),
    id: Int,
    body: UpdateOrderStatusRequest
  ): OrdersResponse {
    return ordersApi.updateOrder(token, id, body).execute().getAsObject()
  }

  @Step("Создать заказ")
  fun createOrder(
    token: String = authHelper.getAdminToken(),
    body: CreateOrderRequest
  ): OrdersResponse {
    return ordersApi.postOrderCreate(token, body).execute().getAsObject()
      .also { GarbageCollector.orders.add(it.id) }
  }
}