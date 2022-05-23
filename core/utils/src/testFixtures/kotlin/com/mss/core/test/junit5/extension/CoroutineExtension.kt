package com.mss.core.test.junit5.extension

import com.mss.core.test.utils.coroutines.TestDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Junit5 extension that provides dispatcher and sets main coroutine dispatcher
 */
@OptIn(ExperimentalCoroutinesApi::class)
internal class CoroutineExtension : BeforeEachCallback, AfterEachCallback {
    override fun beforeEach(context: ExtensionContext) =
        Dispatchers.setMain(TestDispatchers.Main)

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}