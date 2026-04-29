package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.CreateProductResponse
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import backend.helpers.ProductsHelper
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

open class ProductsController : Endpoints() {
  private val authHelper = AuthorizationHelper()
  private val productsHelper = ProductsHelper()

  @Step("Получить все товары")
  fun getAllProducts(
    token: String = authHelper.getAdminToken(),
    offset: Int = 0,
    limit: Int = 50
  ): Response<CreateProductResponse> {
    return productsApi.getAllProducts(token, offset, limit).execute()
  }

  @Step("Получить товар по id")
  fun getProductById(token: String = authHelper.getAdminToken(), id: Int): Response<CreateProductResponse> {
    return productsApi.getProductById(token, id).execute()
  }

  @Step("Создать новый товар")
  fun createProduct(
    token: String = authHelper.getAdminToken(),
    body: CreateProductRequest
  ): Response<CreateProductResponse> {
    return productsApi.postProductCreate(token, body).execute()
      .also { GarbageCollector.products.add(it.getAsObject().id) }
  }

  @Step("Удалить товар по id")
  fun deleteProductById(token: String = authHelper.getAdminToken(), id: Int): Response<ResponseBody> {
    return productsApi.deleteProduct(token, id).execute()
  }

  @Step("Создать новый товар при помощи хелпера")
  fun createProductViaHelper(
    token: String = authHelper.getAdminToken(),
    count: Int,
    name: String,
    price: Double = 1.0,
  ): List<CreateProductResponse> {
    return productsHelper.createProductsViaRepeat(token, count, name, price)
  }
}