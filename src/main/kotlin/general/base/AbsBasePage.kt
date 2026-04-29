package general.base

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.options.LoadState
import com.microsoft.playwright.options.WaitForSelectorState
import general.browser.PageManager

abstract class AbsBasePage<T : AbsBasePage<T>> {

  protected val page: Page = PageManager.get()

  // Переопределяется в наследниках
  protected open fun endpoint(): String = ""

  @Suppress("UNCHECKED_CAST")
  fun open(): T = apply {
    page.navigate(endpoint())
    page.waitForLoadState(LoadState.DOMCONTENTLOADED)
  } as T

  @Suppress("UNCHECKED_CAST")
  fun waitForVisible(locator: Locator): T = apply {
    locator.waitFor(
      Locator.WaitForOptions()
        .setState(WaitForSelectorState.VISIBLE)
    )
  } as T

  @Suppress("UNCHECKED_CAST")
  fun waitForHidden(locator: Locator): T = apply {
    locator.waitFor(
      Locator.WaitForOptions()
        .setState(WaitForSelectorState.HIDDEN)
    )
  } as T

  @Suppress("UNCHECKED_CAST")
  fun waitForDetach(locator: Locator): T = apply {
    locator.waitFor(
      Locator.WaitForOptions()
        .setState(WaitForSelectorState.DETACHED)
    )
  } as T

  @Suppress("UNCHECKED_CAST")
  fun implicitWait(timeoutMs: Double = 3000.0): T = apply {
    page.waitForTimeout(timeoutMs)
  } as T
}