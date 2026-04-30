package backend.helpers

import net.datafaker.Faker

class Utils {

  companion object {
    val faker = Faker()

    fun randomEmailPrefix(minLength: Int = 3, maxLength: Int = 10): String {
      return faker.lorem().characters(minLength, maxLength)
    }

    fun randomUsername(): String {
      return faker.credentials().username()
    }
  }
}