package backend.api.models.orders

data class CreateOrderRequest(
    val userId: Int?,
    val products: List<Product>
) {
    data class Product(
        val id: Int
    )
}