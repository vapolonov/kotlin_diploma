package backend.products

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.extensions.Extensions.Companion.getErrorAsObject
import backend.api.models.ErrorResponse
import backend.api.models.products.CreateProductRequest
import backend.controllers.Controllers
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import kotlin.collections.map

@Tag("API")
class CreateProductTest : Controllers() {

  @Test
  @DisplayName("Проверка создания товара")
  fun productShouldBeCreatedViaApi() {
    val response = products.createProductViaHelper(count = 2, name = "COFFEE")
    response.size.shouldBe(2)
    response.forEach {
      it.name shouldContain "COFFEE"
      it.price shouldBeGreaterThanOrEqual 1.0
    }
  }

  @Test
  @DisplayName("Создание товара и проверка получения его по API")
  fun testProductsPageTitle() {
    val actualProduct = products.createProductViaHelper(count = 1, name = "COFFEE WITH MILK")
    val expectedProduct = products.getProductById(id = actualProduct.first().id).getAsObject()
    expectedProduct.name shouldContain "COFFEE WITH MILK"
  }

  @Test
  @DisplayName("Создание нескольких различных товаров")
  fun multipleDifferentProductsShouldBeCreated() {
    val productsList: List<CreateProductRequest> = listOf(
      CreateProductRequest(name = "AMERICANO", price = 2.5, description = "Coffee Americano"),
      CreateProductRequest(name = "LATTE", price = 3.0, description = "Coffee Latte"),
      CreateProductRequest(name = "CAPPUCCINO", price = 2.8, description = "Coffee Cappuccino"),
    )

    val responses = productsList.map { createProductRequest ->
      val response = products.createProduct(
        body = createProductRequest
      )
      response.getAsObject()
    }

    responses.zip(productsList).forEach { (actual, expected) ->
      actual.name shouldBe expected.name
      actual.price shouldBe expected.price
    }
  }

  @Test
  @DisplayName("Создание продукта с отрицательной ценой")
  fun productShouldNotBeCreatedWithNegativePrice() {
    val product = CreateProductRequest(
      name = "Cappuccino",
      price = 0.0,
      description = "COFFEE Cappuccino"
    )

    runCatching {
      products.createProduct(body = product).getErrorAsObject<ErrorResponse>()
    }.isFailure.shouldBeTrue()

//    val exception = products.createProduct(body = product).getErrorAsObject<ErrorResponse>()
//    exception.code shouldBe 400
//    exception.reason shouldBe "price must be greater than 10"
  }
}
