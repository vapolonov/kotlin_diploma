package frontend.helpers

import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.WaitForSelectorState

class Extensions {

  companion object {

    fun Locator.shouldHaveSizeGreaterThan(size: Int) {
      this.nth(size).waitFor()
    }

    fun Locator.shouldBeVisible() {
      this.waitFor(
        Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)
      )
    }

    fun String.toMoney(): Double {
      val normalized = replace(",", ".")
        .replace(Regex("[^\\d.]"), "")
      return normalized.toDouble()
    }

    fun String.toDigits(): Int {
      return replace("\\D+".toRegex(), "").toInt()
    }

    fun String.toStatus(): String {
      return substringAfter("Status: ").trim()
    }
  }
}