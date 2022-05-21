package com.mss.utils.coroutines

import io.mockk.MockKMatcherScope

fun verify(callee: suspend MockKMatcherScope.() -> Any?): VerifyDispatcherAssert =
    VerifyDispatcherAssertImpl(callee)