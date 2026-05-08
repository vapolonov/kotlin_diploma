package frontend.products

import frontend.pages.MainPage
import frontend.pages.ProductsPage
import general.jupiter.annotations.UITest
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@UITest
@Tag("UI")
@Story("Тесты на страницу товаров")
class ProductsTest {

  @Test
  @DisplayName("Проверка заголовка страницы Products")
  fun testProductsPageTitle() {
    MainPage()
      .open()
      .header()
      .clickLink("Products")
    val title = ProductsPage()
      .getTitle()

    title shouldBe "All Products"
  }

  @Test
  @DisplayName("Проверка что популярные продукты, есть на странице Products")
  fun testPopularProducts() {
    MainPage()
      .open()
      .getPopularProducts()[1]
      .btnIncrement
      .click()

    MainPage()
      .header()
      .clickLink("Products")

    val secondProductsItem = ProductsPage()
      .getProductsItems()[1]

    val secondPopularProduct = MainPage().getPopularProducts()[1]

    secondPopularProduct shouldBeEqual secondProductsItem
    secondPopularProduct.quantity shouldBeEqual secondProductsItem.quantity
  }

  @Test
  @DisplayName("Сравнение популярных товаров с товарами на странице Products")
  fun testAllProducts() {
    val popularProducts = MainPage()
      .open()
      .getPopularProducts()
      .sortedBy { it.name }

    MainPage()
      .header()
      .clickLink("Products")

    val allProductsItems = ProductsPage()
      .getProductsItems()

    allProductsItems.sortedBy { it.name } shouldContainAll popularProducts
  }
}