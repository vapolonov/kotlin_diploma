package general

import backend.api.extensions.Extensions.Companion.getAsObject
import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import database.JDBCHelper
import general.config.Config
import io.kotest.matchers.ints.shouldBeGreaterThan
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan

class TestListener : Controllers(), TestExecutionListener {
  val authHelper = AuthorizationHelper()

  override fun testPlanExecutionStarted(testPlan: TestPlan) {
    println("<-----Starting Test Plan execution----->")
    println("Init Configurations").also { Config.get }
  }

  override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
    if (testIdentifier.isTest) println("Skipping test: ${testIdentifier.displayName} - Reason: $reason")
  }

  override fun executionFinished(testIdentifier: TestIdentifier, testExecutionResult: TestExecutionResult) {
    if (testIdentifier.isTest) println("Finished test: ${testIdentifier.displayName} - Reason: ${testExecutionResult.status}")
  }

  override fun testPlanExecutionFinished(testPlan: TestPlan) {
    println("<-----Garbage Collector----->")

    GarbageCollector.orders.forEach { id ->
      val deletedOrders = JDBCHelper().deleteOrderById(id).also { println("Deleted order: $id") }
      deletedOrders shouldBeGreaterThan 0
    }

    GarbageCollector.users.forEach { id ->
      users.deleteUserById(authHelper.getAdminToken(), id = id).also { println("Deleted User: $id") }
    }

    users.getAllUsers(token = authHelper.getAdminToken(), offset = 1, limit = 50).getAsObject().forEach { user ->
      if (user.email.endsWith("@autotest.com")) {
        users.deleteUserById(authHelper.getAdminToken(), id = user.id).also { println("Deleted User: ${user.email}") }
      }
    }

    GarbageCollector.products.forEach { id ->
      products.deleteProductById(authHelper.getAdminToken(), id = id).also { println("Deleted product: $id") }
    }

    GarbageCollector.users.clear()
    GarbageCollector.products.clear()
    GarbageCollector.orders.clear()
    println("<-----Finished Test Plan execution----->")
  }
}