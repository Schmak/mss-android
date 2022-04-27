package com.mss.junit5.extension

import com.mss.extensions.getField
import com.mss.junit5.ITestCaseWithOrigin
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestTemplateInvocationContext
import org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor
import org.junit.platform.commons.util.ReflectionUtils

/**
 * Junit5 extension that prints `constructedAt` field of any
 * [ITestCaseWithOrigin] passed in `ParameterizedTest`. It allows the
 * developer to jump to the line where the test was created.
 */
internal class PrintTestCaseOriginExtension : BeforeEachCallback {
    override fun beforeEach(context: ExtensionContext) =
        argumentsFrom(context)
            .mapNotNull { (it as? ITestCaseWithOrigin)?.constructedAt }
            .forEach(::println)

    // https://github.com/junit-team/junit5/issues/1139#issuecomment-573987834
    @Suppress("NewApi")
    private fun argumentsFrom(context: ExtensionContext): List<Any?> = runCatching {
        val method = ReflectionUtils.findMethod(context::class.java, "getTestDescriptor").orElse(null)
        val descriptor = ReflectionUtils.invokeMethod(method, context) as TestMethodTestDescriptor
        val template = descriptor.getField<TestTemplateInvocationContext>("invocationContext")
        val arguments = template.getField<Array<*>>("arguments")
        return arguments.toList()
    }.getOrDefault(emptyList())
}