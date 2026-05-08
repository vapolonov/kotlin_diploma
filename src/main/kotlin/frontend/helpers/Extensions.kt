package frontend.helpers

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
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

    fun Locator.findByGroupId(target: String): Locator {
      return this.locator("[data-test-group='$target']")
    }

    fun Page.findByGroupId(target: String): Locator {
      return this.locator("[data-test-group='$target']")
    }

    fun Locator.findByTestId(target: String): Locator {
      return this.getByTestId(target)
    }

    fun Page.findByTestId(target: String): Locator {
      return this.getByTestId(target)
    }

//    fun String.toMoney(): Double {
//      val normalized = replace(",", ".")
//        .replace(Regex("[^\\d.]"), "")
//      return normalized.toDouble()
//    }

    fun String.toMoney(): Double {
      return this.filter { it.isDigit() }.toDouble() / 100F
    }

    fun String.toDigits(): Int {
      return replace("\\D+".toRegex(), "").toInt()
    }

    fun String.toStatus(): String {
      return substringAfter("Status: ").trim()
    }
  }
}