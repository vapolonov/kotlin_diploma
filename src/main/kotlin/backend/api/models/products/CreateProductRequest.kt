package backend.api.models.products

data class CreateProductRequest(
    val name: String,
    val price: Double,
    val description: String
)