package backend.api.models.orders

data class UpdateOrderStatusRequest(
    val orderStatus: String
)