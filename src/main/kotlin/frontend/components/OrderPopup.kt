package frontend.components

import backend.helpers.GarbageCollector
import com.microsoft.playwright.Locator
import frontend.helpers.Extensions.Companion.findByTestId
import frontend.helpers.Extensions.Companion.toDigits
import frontend.helpers.Extensions.Companion.toStatus
import general.browser.PageManager

class OrderPopup : BaseComponent<OrderPopup>(
  PageManager.get().locator(".order-popup")
) {

  private val orderId: Locator get() = root.findByTestId("order-popup-id")
  private val orderStatus: Locator get() = root.findByTestId("order-popup-status")
  private val orderCloseBtn: Locator get() = root.findByTestId("order-popup-status")

  fun getOrderId(): Int {
    return orderId.textContent().toDigits()
      .also { GarbageCollector.orders.add(it) }
  }

  fun getOrderStatus(): String {
    return orderStatus.textContent().toStatus()
  }

  fun closeOrderPopup(): OrderPopup {
    orderCloseBtn.click()
    return this
  }
}
