package backend.helpers

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.CreateProductResponse
import backend.controllers.Controllers
import io.qameta.allure.Step

class ProductsHelper : Controllers() {

  @Step("Create number of products: {count}")
  fun createProducts(count: Int): List<CreateProductRequest> {
    val listOfProducts = mutableListOf<CreateProductRequest>()
    for (i in 1..count) {
      listOfProducts.add(
        CreateProductRequest(
          name = "Product #$i",
          description = "Description for product #$i",
          price = i.toDouble()
        )
      )
    }
    listOfProducts.forEach {
      products.createProduct(body = it)
    }
    return listOfProducts.toList()
  }

  @Step("Create number of products: {count}")
  fun createProductsViaRepeat(token: String, count: Int, name: String, price: Double): List<CreateProductResponse> {
    val listOfProducts = mutableListOf<CreateProductResponse>()

    repeat( count) { index ->
      listOfProducts.add(
        products.createProduct(
          token = token,
          body = CreateProductRequest(
            name = "$name #$index",
            description = "Description for product #$index",
            price = price + 1.toDouble(),
          )
        ).getAsObject()
      )

    }
    return listOfProducts.toList()
  }
}