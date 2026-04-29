package database.helpers

import backend.api.models.orders.OrdersResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import database.models.OrdersEntity
import database.models.ProductsEntity
import database.models.UsersEntity
import java.sql.ResultSet

class Extensions {
  companion object {
    fun ResultSet.toProduct() = ProductsEntity(
      id = getInt("id"),
      name = getString("name"),
      description = getString("description"),
      price = getDouble("price"),
    )

    fun ResultSet.toUser() = UsersEntity(
      id = getInt("id"),
      username = getString("username"),
      email = getString("email"),
      password = getString("password"),
    )


    fun ResultSet.toOrder(): OrdersEntity {
      val productsJson = getString("product")
      val type = object : TypeToken<List<OrdersResponse.Product>>() {}.type
      val products: List<OrdersResponse.Product> = Gson().fromJson(productsJson, type)

      return OrdersEntity(
        id = getInt("id"),
        userId = getInt("userId"),
        orderStatus = getInt("orderStatus"),
        products = products,
        totalAmount = getDouble("totalAmount"),
        createdAt = getInt("createdAt"),
        updatedAt = getInt("updatedAt")
      )
    }

  }
}