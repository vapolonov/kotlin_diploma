package general.config

data class PropsModel(
  val frontendUrl: String,
  val backendUrl: String,
  val selenoidUrl: String,
  val browserHeadless: String,
  val browserSlowMo: String,
  val dbUrl: String,
  val dbUsername: String,
  val dbPassword: String,
)