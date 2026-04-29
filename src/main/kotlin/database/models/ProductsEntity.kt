package database.models

data class ProductsEntity(
  val id: Int,
  val name: String,
  val description: String,
  val price: Double,
)