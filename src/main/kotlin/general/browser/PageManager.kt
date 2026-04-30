package general.browser

import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page

object PageManager {
  private val threadLocalPage = ThreadLocal<Page>()
  private val threadLocalContext = ThreadLocal<BrowserContext>()

  fun set(page: Page?) = threadLocalPage.set(page)
  fun get(): Page = requireNotNull(threadLocalPage.get()) { "Page is not initialized" }

  fun setContext(context: BrowserContext?) = threadLocalContext.set(context)
  fun getContext(): BrowserContext = requireNotNull(threadLocalContext.get()) { "BrowserContext is not initialized" }

  fun remove() {
    threadLocalPage.remove()
    threadLocalContext.remove()
  }
}