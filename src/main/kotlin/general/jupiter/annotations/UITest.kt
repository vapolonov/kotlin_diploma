package general.jupiter.annotations

import general.jupiter.AllureExtension
import general.jupiter.UIExtension
import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ExtendWith(UIExtension::class, AllureExtension::class)
annotation class UITest