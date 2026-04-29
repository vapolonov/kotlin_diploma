package backend.api.endpoints

import backend.RetrofitClient

open class Endpoints {

    protected val authApi: AuthEndpoints by lazy { RetrofitClient.createService(AuthEndpoints::class.java) }
    protected val usersApi: UsersEndpoints by lazy { RetrofitClient.createService(UsersEndpoints::class.java) }
    protected val productsApi: ProductsEndpoints by lazy { RetrofitClient.createService(ProductsEndpoints::class.java) }
    protected val ordersApi: OrdersEndpoints by lazy { RetrofitClient.createService(OrdersEndpoints::class.java) }
}