package general.config

import java.io.FileInputStream
import java.util.Properties

object Config {

  val get: PropsModel by lazy {
    val env = System.getProperty("env", "tst")
    val props = Properties().apply { load(FileInputStream("src/main/resources/$env.properties")) }

    PropsModel(
      frontendUrl = props.getProperty("frontend.url", "http://localhost:4000"),
      backendUrl = props.getProperty("backend.url", "http://localhost:1111/api/v1"),
      selenoidUrl = props.getProperty("selenoid.url", "https://selenoid.autotests.cloud"),
      browserHeadless = props.getProperty("browser.headless", "false"),
      browserSlowMo = props.getProperty("browser.slowMo", "100"),
      dbUrl = props.getProperty("db.url", "jdbc:postgresql://localhost:5432/playground"),
      dbUsername = props.getProperty("db.username", "postgres"),
      dbPassword = props.getProperty("db.password", "postgres"),
    )
  }
}