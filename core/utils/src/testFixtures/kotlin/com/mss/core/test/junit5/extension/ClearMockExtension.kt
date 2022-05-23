package com.mss.core.test.junit5.extension

import com.mss.core.extensions.filterNotNullValues
import com.mss.core.extensions.nullIfEmpty
import com.mss.core.test.extensions.isMock
import io.mockk.clearMocks
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.isAccessible

/**
 * JUnit5 extension that clears mockks stored in test fields after each
 * test
 */
@Suppress("SpreadOperator")
internal class ClearMockExtension : AfterEachCallback {
    private val properties = mutableMapOf<KClass<*>, List<KProperty<*>>>()

    @Suppress("NewApi")
    override fun afterEach(context: ExtensionContext) {
        val testInstance = context.testInstance.orElse(null) ?: return
        val mockMap = properties
            .getOrPut(testInstance::class) {
                testInstance::class.members
                    .filterIsInstance<KProperty1<*, *>>()
                    .onEach { it.isAccessible = true }
            }
            .associate { it.name to it.call(testInstance) }
            .filterNotNullValues()
            .filter { (_, value) -> value.isMock }
            .nullIfEmpty ?: return
        val mocks = mockMap.values.toTypedArray()

//        println("Cleared ${testInstance::class.simpleName} mocks: $mockMap")
        clearMocks(mocks[0], *mocks)
    }
}