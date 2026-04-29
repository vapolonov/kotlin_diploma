package general.browser

import com.microsoft.playwright.APIRequestContext
import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright

object PageManager {
  private val threadLocalPage = ThreadLocal<Page>()
  private val threadLocalContext = ThreadLocal<BrowserContext>()
  private val threadLocalApiContext = ThreadLocal<APIRequestContext>()
  private val threadLocalBrowser = ThreadLocal<Browser>()
  private val threadLocalPlaywright = ThreadLocal<Playwright>()

  fun set(page: Page?) = threadLocalPage.set(page)
  fun get(): Page = requireNotNull(threadLocalPage.get()) { "Page is not initialized" }

  fun setContext(context: BrowserContext?) = threadLocalContext.set(context)
  fun getContext(): BrowserContext = requireNotNull(threadLocalContext.get()) { "BrowserContext is not initialized" }
//
//  fun setBrowser(browser: Browser) = threadLocalBrowser.set(browser)
//  fun getBrowser(): Browser = requireNotNull(threadLocalBrowser.get()) { "Browser is not initialized" }
//
//  fun setPlaywright(playwright: Playwright) = threadLocalPlaywright.set(playwright)
//  fun getPlaywright(): Playwright = requireNotNull(threadLocalPlaywright.get()) { "Playwright is not initialized" }
//
//  fun setApiContext(apiContext: APIRequestContext) = threadLocalApiContext.set(apiContext)
//  fun getApiContext(): APIRequestContext = requireNotNull(threadLocalApiContext.get()) { "APIRequestContext is not initialized" }

  fun remove() {
    threadLocalPage.remove()
    threadLocalContext.remove()
//    threadLocalApiContext.remove()
//    threadLocalBrowser.remove()
//    threadLocalPlaywright.remove()
  }
}