package com.mss.utils.coroutines

import kotlin.coroutines.ContinuationInterceptor

interface VerifyDispatcherAssert {
    infix fun isCalledFrom(caller: suspend () -> Any?): WithCaller

    interface WithCaller {
        suspend infix fun with(expectedInterceptor: ContinuationInterceptor)
    }
}