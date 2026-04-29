package database.models

import backend.api.models.orders.OrdersResponse

data class OrdersEntity(
  val id: Int,
  val userId: Int,
  val orderStatus: Int,
  val products: List<OrdersResponse.Product>,
  val totalAmount: Double,
  val createdAt: Int,
  val updatedAt: Int,
)