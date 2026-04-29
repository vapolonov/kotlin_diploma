package backend.api.endpoints

import backend.api.endpoints.headers.Headers
import backend.api.models.products.CreateProductRequest
import backend.api.models.products.CreateProductResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsEndpoints {

  @POST("products/create")
  fun postProductCreate(
    @Header(Headers.AUTHORIZATION) token: String,
    @Body body: CreateProductRequest
  ) : Call<CreateProductResponse>

  @GET("products/{id}")
  fun getProductById(
    @Header(Headers.AUTHORIZATION) token: String,
    @Path("id") id: Int
  ): Call<CreateProductResponse>

  @GET("products/")
  fun getAllProducts(
    @Header(Headers.AUTHORIZATION) token: String,
    @Query("offset") offset: Int,
    @Query("limit") limit: Int,
  ): Call<CreateProductResponse>

  @DELETE("products/{id}")
  fun deleteProduct(
    @Header(Headers.AUTHORIZATION) token : String,
    @Path("id") id : Int
  ) : Call<ResponseBody>
}