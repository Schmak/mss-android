package com.mss.utils.coroutines

import io.mockk.MockKMatcherScope
import io.mockk.coEvery
import kotlinx.coroutines.currentCoroutineContext
import org.assertj.core.api.Assertions
import kotlin.coroutines.ContinuationInterceptor

internal class VerifyDispatcherAssertImpl(
    private val callee: suspend MockKMatcherScope.() -> Any?,
) : VerifyDispatcherAssert {
    override infix fun isCalledFrom(caller: suspend () -> Any?) = WithCaller(callee, caller)

    class WithCaller(
        private val blockUnderTest: suspend MockKMatcherScope.() -> Any?,
        private val block: suspend () -> Any?
    ) : VerifyDispatcherAssert.WithCaller {
        override suspend infix fun with(expectedInterceptor: ContinuationInterceptor) {
            var dispatcher: ContinuationInterceptor? = null
            coEvery(blockUnderTest) coAnswers {
                dispatcher = currentCoroutineContext()[ContinuationInterceptor]
                null
            }
            block()
            Assertions.assertThat(dispatcher).isEqualTo(expectedInterceptor)
        }
    }
}