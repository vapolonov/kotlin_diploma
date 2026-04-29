package general.browser

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType.LaunchOptions
import com.microsoft.playwright.Playwright
import general.config.Config

class BrowserFactory(private val playwright: Playwright) {

  private val browserType: String = System.getProperty("browser.type", "chrome")

  val launchOptions: LaunchOptions = LaunchOptions()
      .setHeadless(Config.get.browserHeadless.toBoolean())
      .setSlowMo(Config.get.browserSlowMo.toDouble())

  fun create(): Browser {
    return when (browserType) {
      "chrome" -> playwright.chromium().launch(launchOptions)
      "firefox" -> playwright.firefox().launch(launchOptions)
      "webkit" -> playwright.webkit().launch(launchOptions)
      else -> error("Unknown browser type: $browserType")
    }
  }
}