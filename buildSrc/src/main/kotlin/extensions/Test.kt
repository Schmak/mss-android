package extensions

import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestResult
import org.gradle.kotlin.dsl.KotlinClosure2

@Suppress("unused", "LongLine", "MaxLineLength", "MagicNumber")
val Test.printSuiteResult: KotlinClosure2<TestDescriptor, TestResult, Void>
    get() = KotlinClosure2({ desc, result ->
        if (desc.parent == null) {
            with(result) {
                val width = 80
                println(
                    """
                        ${desc.name.padStart((width + desc.name.length) / 2, '-').padEnd(width, '-')}
                        Results: $resultType ($testCount tests, $successfulTestCount successes, $failedTestCount failures, $skippedTestCount skipped)
                        ${"-".repeat(width)}
                    """.trimIndent()
                )
            }
        }
        null
    })