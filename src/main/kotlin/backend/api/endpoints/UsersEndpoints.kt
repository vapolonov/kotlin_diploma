package backend.api.endpoints

import backend.api.endpoints.headers.Headers
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.UpdateUserRequest
import backend.api.models.users.UserResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersEndpoints {

  @GET("/users/{id}")
  fun getUserById(
    @Header(Headers.AUTHORIZATION) token: String,
    @Path("id") id: Int
  ): Call<UserResponse>

  @GET("/users/me}")
  fun getUserMe(
    @Header(Headers.AUTHORIZATION) token: String,
  ): Call<UserResponse>

  @POST("users/create")
  fun postUserCreate(@Body body: CreateUserRequest) : Call<UserResponse>

  @DELETE("users/{id}")
  fun deleteUser(
    @Header(Headers.AUTHORIZATION) token : String,
    @Path("id") id : Int
  ) : Call<ResponseBody>

  @PUT("users/{id}")
  fun updateUser(
    @Header(Headers.AUTHORIZATION) token: String,
    @Path("id") id: Int,
    @Body body: UpdateUserRequest,
  ) : Call<UserResponse>

  @GET("users/")
  fun getAllUsers(
    @Header(Headers.AUTHORIZATION) token: String,
    @Query("offset") offset: Int,
    @Query("limit") limit: Int,
  ) : Call<List<UserResponse>>
}