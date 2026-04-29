package backend.api.endpoints

import backend.api.endpoints.headers.Headers
import backend.api.models.orders.CreateOrderRequest
import backend.api.models.orders.OrdersResponse
import backend.api.models.orders.UpdateOrderStatusRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrdersEndpoints {

  @GET("orders/")
  fun getOrders(
    @Header(Headers.AUTHORIZATION) token: String,
    @Query("offset") offset: Int,
    @Query("limit") limit: Int,
  ): Call<List<OrdersResponse>>

  @GET("orders/{id}")
  fun getOrderById(
    @Header(Headers.AUTHORIZATION) token: String,
    @Path("id") id: Int
  ): Call<OrdersResponse>

  @GET("orders/user/{id}")
  fun getOrderByUserId(
    @Header(Headers.AUTHORIZATION) token: String,
    @Path("id") id: Int
  ): Call<List<OrdersResponse>>

  @POST("orders/create")
  fun postOrderCreate(
    @Header(Headers.AUTHORIZATION) token: String,
    @Body body: CreateOrderRequest
  ) : Call<OrdersResponse>

  @PUT("orders/{id}/status")
  fun updateOrder(
    @Header(Headers.AUTHORIZATION) token: String,
    @Path("id") id: Int,
    @Body body: UpdateOrderStatusRequest,
  ) : Call<OrdersResponse>

}