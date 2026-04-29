package frontend.components

import backend.helpers.GarbageCollector
import com.microsoft.playwright.Locator
import frontend.helpers.Extensions.Companion.toDigits
import frontend.helpers.Extensions.Companion.toStatus
import general.browser.PageManager
import io.qameta.allure.Step

class OrderPopup : AbsBaseComponent<OrderPopup>(
  PageManager.get().locator(".order-popup")
) {

  private val orderId: Locator get() = root.getByTestId("order-popup-id")
  private val orderStatus: Locator get() = root.getByTestId("order-popup-status")
  private val orderCloseBtn: Locator get() = root.getByTestId("order-popup-status")

//  @Step("Получить id заказа")
  fun getOrderId(): Int {
    return orderId.textContent().toDigits()
      .also { GarbageCollector.orders.add(it) }
  }


  @Step("Получить статус заказа")
  fun getOrderStatus(): String {
    return orderStatus.textContent().toStatus()
  }

  @Step("Закрыть попап заказа")
  fun closeOrderPopup(): OrderPopup = apply {
    orderCloseBtn.click()
  }
}

