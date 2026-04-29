package database

import database.helpers.Extensions.Companion.toProduct
import database.helpers.Extensions.Companion.toUser
import database.models.ProductsEntity
import database.models.UsersEntity
import general.config.Config
import java.sql.DriverManager

class JDBCHelper {

  private val client = DriverManager.getConnection(
    Config.get.dbUrl,
    Config.get.dbUsername,
    Config.get.dbPassword
  )

  fun getProducts(): List<ProductsEntity> =
    client.use { conn ->
      conn.createStatement().use { stmt ->
        stmt.executeQuery("SELECT * FROM table_products").use { rs ->
          generateSequence { rs.takeIf { it.next() }?.toProduct() }.toList()
        }
      }
    }

  fun getUserByEmail(email: String): UsersEntity? =
    client.use { conn ->
      conn.prepareStatement("SELECT * FROM table_users WHERE email = ?").use { stmt ->
        stmt.setString(1, email)
        stmt.executeQuery().use { rs ->
          rs.takeIf { it.next() }?.toUser()
        }
      }
    }

  fun deleteOrderById(orderId: Int): Int {
    client.use { conn ->
      conn.prepareStatement("DELETE FROM table_orders WHERE id = ?").use { stmt ->
        stmt.setInt(1, orderId)
        return stmt.executeUpdate()
      }
    }
  }
}
