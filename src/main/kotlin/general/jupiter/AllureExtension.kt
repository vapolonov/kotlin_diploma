package general.jupiter

import com.microsoft.playwright.Page
import com.microsoft.playwright.Tracing
import general.browser.PageManager
import io.qameta.allure.Attachment
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.io.File

class AllureExtension : TestWatcher, AfterTestExecutionCallback, AfterEachCallback {

  override fun afterTestExecution(context: ExtensionContext) {
    if (context.executionException.isPresent) {
      val page = PageManager.get()
      runCatching {
        attachScreenshot(page)
      }.onFailure {
        println("Screenshot failed: ${it.message}")
      }
    }
  }

  override fun afterEach(context: ExtensionContext) {
    if (context.executionException.isPresent) {
      val browserContext = PageManager.getContext()
      val testName = context.displayName
      val traceFile = File("build/artifacts/$testName/trace.zip").apply {
        parentFile.mkdirs()
      }
      runCatching {
        browserContext.tracing().stop(
          Tracing.StopOptions().setPath(traceFile.toPath())
        )
      }.onSuccess {
        if (traceFile.exists()) {
          attachTrace(traceFile)
        }
      }.onFailure {
        println("Trace failed: ${it.message}")
      }
    }
  }

  // --- Attachments ---
  @Attachment(value = "Screenshot", type = "image/png")
  fun attachScreenshot(page: Page): ByteArray {
    return page.screenshot()
  }

  @Attachment(value = "Playwright trace", type = "application/zip")
  fun attachTrace(file: File): ByteArray = file.readBytes()
}