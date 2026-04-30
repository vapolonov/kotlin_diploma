package frontend

import frontend.pages.MainPage
import general.jupiter.annotations.UITest
import io.kotest.matchers.collections.shouldContainAll
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@UITest
@Tag("UI")
class MainPageTest {

  @Test
  fun mainPageShouldContainPopularProducts() {
    MainPage()
      .open()
      .checkPageLoaded()
      .checkNumberOfPopularProducts()
  }

  @ParameterizedTest
  @ValueSource(strings = ["Products", "Orders", "Contact", " Cart "])
  fun mainMenuShouldContainAllLinks(links: String) {
    val listLinks = MainPage()
      .open()
      .header()
      .getLinks()

    listLinks.shouldContainAll(links)
  }
}
