package backend.api.extensions

import com.google.gson.Gson
import io.kotest.assertions.AssertionErrorBuilder.Companion.fail
import io.qameta.allure.Step
import retrofit2.Call
import retrofit2.Response

class Extensions {
  companion object {

    @Step("Check if response is successful")
    fun <T> Response<T>.checkIsSuccessful(): Boolean {
      if (!this.isSuccessful) fail("Response was not successful: code=${code()}")
      return true
    }

    @Step("Get response body as object of type {T}")
    inline fun <reified T> Response<T>.getAsObject(): T {
      return try {
        body()!!
      } catch (e: Exception) {
        throw Error(
          "Response body is null or cannot be cast to the specified type: body: ${body()} | errorBody: ${errorBody()?.string()}",
          e
        )
      }
    }

    @Step("Get error body as object of type {R}")
    inline fun <reified R> Response<*>.getErrorAsObject(): R {
      return try {
        Gson().fromJson(errorBody()?.string().orEmpty(), R::class.java)
      } catch (e: Exception) {
        throw Error(
          "Error body is null or cannot be cast to the specified type: errorBody: ${errorBody()?.string()}",
          e
        )
      }
    }

    @Step("Get error body as string")
    inline fun <reified T> Response<T>.getErrorBody(): String {
      return errorBody()?.string() ?: ""
    }

    inline fun <reified T> Call<T>.retryIfEmpty(count: Int): Response<T> {
      var i = 1
      var res: Response<T>
      do {
        res = this.clone().execute()
        if ((res.getAsObject() as List<*>).isNotEmpty()) break
        if ( i == count) fail("Request body is empty") else i++
      } while ((res.getAsObject() as List<*>).isNotEmpty())
      return res
    }

    @Step("String to Bearer token")
    fun String.toBearer(): String {
      return "Bearer $this"
    }
  }
}