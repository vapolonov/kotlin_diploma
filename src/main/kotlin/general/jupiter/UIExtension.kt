package general.jupiter

import com.microsoft.playwright.*
import general.browser.BrowserFactory
import general.browser.PageManager
import general.config.Config
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

class UIExtension : BeforeEachCallback, AfterEachCallback {

  private var playwright: Playwright? = null
  private var browser: Browser? = null
  private var browserContext: BrowserContext? = null
  private var apiContext: APIRequestContext? = null

  override fun beforeEach(contextExt: ExtensionContext) {
    playwright = Playwright.create().also {
      it.selectors().setTestIdAttribute("data-test-id")
    }
    browser = BrowserFactory(playwright!!).create()
    browserContext = browser?.newContext(
      Browser.NewContextOptions()
        .setViewportSize(1920, 1080).setBaseURL(Config.get.frontendUrl)
    )
    val page = browserContext?.newPage()

    browserContext?.tracing()?.start(
      Tracing.StartOptions()
        .setScreenshots(true)
        .setSnapshots(true)
        .setSources(true)
    )

    PageManager.set(page)
    PageManager.setContext(browserContext)

    apiContext = playwright!!.request().newContext(
      APIRequest.NewContextOptions().setBaseURL(Config.get.backendUrl)
    )
  }

  override fun afterEach(contextExt: ExtensionContext) {
    PageManager.get().close()
    browserContext?.close()
    apiContext?.dispose()
    browser?.close()
    playwright?.close()
    PageManager.remove()
  }
}