package frontend.components

import com.microsoft.playwright.Locator

abstract class BaseComponent<T : BaseComponent<T>>(val root: Locator) {

  @Suppress("UNCHECKED_CAST")
  protected fun root(): T = this as T

}
