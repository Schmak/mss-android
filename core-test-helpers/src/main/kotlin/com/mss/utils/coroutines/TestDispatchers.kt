package com.mss.utils.coroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
@Suppress("unused")
object TestDispatchers {
    private val scheduler = TestCoroutineScheduler()

    val Main = StandardTestDispatcher(scheduler, name = "MainDispatcher")
    val IO = StandardTestDispatcher(scheduler, name = "IODispatcher")
    val Default = StandardTestDispatcher(scheduler, name = "DefaultDispatcher")
    val Unconfined = UnconfinedTestDispatcher(scheduler, name = "UnconfinedDispatcher")
}