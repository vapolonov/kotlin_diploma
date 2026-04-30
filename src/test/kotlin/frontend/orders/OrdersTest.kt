package frontend.orders

import StatusEnum
import backend.api.extensions.Extensions.Companion.getAsObject
import backend.api.models.orders.UpdateOrderStatusRequest
import backend.controllers.Controllers
import backend.helpers.UserHelper
import frontend.components.CartPopup
import frontend.components.Header
import frontend.components.OrderPopup
import frontend.helpers.Extensions.Companion.shouldBeVisible
import frontend.pages.MainPage
import frontend.pages.ProductsPage
import general.jupiter.annotations.UITest
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@UITest
@Tag("UI")
class OrdersTest : Controllers() {

  @Test
  @DisplayName("Создание товара c главной страницы")
  fun shouldCreateOrderFromMainPage() {
    val firstPopularProduct = MainPage().open()
      .getPopularProducts()
      .first()
    firstPopularProduct.btnIncrement.click()

    MainPage().header().clickLink("Cart")
    val cartProduct = CartPopup().getCartProducts()
    cartProduct.size shouldBe 1

    CartPopup().createOrder()

    val popup = OrderPopup()
    val orderId = popup.getOrderId()
    val orderStatus = popup.getOrderStatus()

    val orderViaApi = orders.getOrderById(id = orderId)

    orderViaApi.id shouldBe orderId
    orderViaApi.orderStatus shouldBe orderStatus
  }

  @Test
  @DisplayName("Создание товара авторизованным пользователем")
  fun shouldCreateOrderByUser() {
    val password = "random"
    val testUser = UserHelper().createUser()
    val userId = testUser.id

    MainPage().open()
      .openLoginForm()
      .authPopup()
      .fillLoginForm(testUser.email, password)
      .submitLogin()

    MainPage().header()
      .checkAvatar()
      .clickLink("Products")
      .toProducts()
      .getProductsItems()[2]
      .btnIncrement.click()

    Header().clickLink("Cart")
    CartPopup().createOrder()
    val popup = OrderPopup()
    val orderId = popup.getOrderId()
    val orderStatus = popup.getOrderStatus()
    popup.closeOrderPopup()

    val userOrder = orders.getOrderByUserId(id = userId)

    userOrder shouldHaveSize 1
    // Вариант 1
    userOrder[0].userId shouldBe userId
    userOrder[0].id shouldBe orderId
    userOrder[0].orderStatus shouldBe orderStatus

    // Вариант 2
    userOrder.forEachIndexed { _, order ->
      order.userId shouldBe userId
      order.id shouldBe orderId
      order.orderStatus shouldBe orderStatus
    }
  }

  @Test
  @DisplayName("Создание нескольких товаров ")
  fun shouldCreateSeveralOrder() {
    val products = ProductsPage().open()
      .getProductsItems()

    products.forEach { it.btnIncrement.click() }

    ProductsPage().header().clickLink("Cart")
    val cartProducts = CartPopup().getCartProducts()
    cartProducts.size shouldBeGreaterThan 0

    CartPopup().createOrder()

    val popup = OrderPopup()
    val orderId = popup.getOrderId()
    val orderStatus = popup.getOrderStatus()

    val ordersViaApi = orders.getAllOrders()

    val expectedOrder = ordersViaApi.first { it.id == orderId }
    expectedOrder.orderStatus shouldBe orderStatus
    expectedOrder.products.size shouldBe cartProducts.size

  }

  @ParameterizedTest
  @CsvSource(
    StatusEnum.IN_PROGRESS,
    StatusEnum.COMPLETED
  )
  @DisplayName("Изменение статуса заказа")
  fun shouldChangeOrderStatusViaApi(status: String) {

    ProductsPage().open()
      .getProductsItems().first().btnIncrement.click()

    MainPage().header().clickLink("Cart")
    val cart = CartPopup()
    cart.getCartProducts().size shouldBe 1
    cart.createOrder()

    val popup = OrderPopup()
    val orderId = popup.getOrderId()
    popup.getOrderStatus() shouldBe StatusEnum.PENDING

    val changedOrder = orders.updateOrderById(id = orderId, body = UpdateOrderStatusRequest(status))

    changedOrder.orderStatus shouldBe status
  }
}