package frontend.elements

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.WaitForSelectorState

class Button(private val locator: Locator) {

  fun click() = apply {
    locator.isVisible
    locator.click()
  }

  fun shouldBeVisible() = apply {
    locator.waitFor(
      Locator.WaitForOptions()
        .setState(WaitForSelectorState.VISIBLE)
    )
  }
}