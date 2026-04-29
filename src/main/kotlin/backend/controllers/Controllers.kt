package backend.controllers

open class Controllers {
  val auth get() = AuthController()
  val users get() = UsersController()
  val products get() = ProductsController()
  val orders get() = OrdersController()
}