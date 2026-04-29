package backend.api.models.orders

data class OrdersResponse(
    val id: Int,
    val userId: Int?,
    val orderStatus: String,
    val products: List<Product>,
    val totalAmount: Double,
    val createdAt: Long,
    val updatedAt: Long
) {
    data class Product(
        val id: Int,
        val name: String,
        val price: Double,
        val description: String
    )
}