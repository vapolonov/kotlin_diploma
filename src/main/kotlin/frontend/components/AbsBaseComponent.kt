package frontend.components

import com.microsoft.playwright.Locator

abstract class AbsBaseComponent<T : AbsBaseComponent<T>>(val root: Locator) {

  @Suppress("UNCHECKED_CAST")
  protected fun root(): T = this as T

}
