package general.base

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.LoadState
import com.microsoft.playwright.options.WaitForSelectorState
import general.browser.PageManager

abstract class BasePage<T : BasePage<T>> {

  protected val page: Page = PageManager.get()
  protected open fun endpoint(): String = ""

  @Suppress("UNCHECKED_CAST")
  fun open(): T {
    page.navigate(endpoint())
    page.waitForLoadState()
    return this as T
  }

  @Suppress("UNCHECKED_CAST")
  fun waitForVisible(locator: Locator): T {
    locator.waitFor(
      Locator.WaitForOptions()
        .setState(WaitForSelectorState.VISIBLE)
    )
    return this as T
  }

  @Suppress("UNCHECKED_CAST")
  fun waitForHidden(locator: Locator): T {
    locator.waitFor(
      Locator.WaitForOptions()
        .setState(WaitForSelectorState.HIDDEN)
    )
    return this as T
  }

  @Suppress("UNCHECKED_CAST")
  fun waitForDetach(locator: Locator): T {
    locator.waitFor(
      Locator.WaitForOptions()
        .setState(WaitForSelectorState.DETACHED)
    )
    return this as T
  }

  @Suppress("UNCHECKED_CAST")
  fun implicitWait(timeoutMs: Double = 3000.0): T {
    page.waitForTimeout(timeoutMs)
    return this as T
  }
}