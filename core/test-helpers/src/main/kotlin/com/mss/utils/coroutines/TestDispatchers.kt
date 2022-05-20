package com.mss.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
@Suppress("unused")
object TestDispatchers {
    private val scheduler = TestCoroutineScheduler()

    val Main: CoroutineDispatcher =
        StandardTestDispatcher(scheduler, name = "MainDispatcher")

    val IO: CoroutineDispatcher =
        StandardTestDispatcher(scheduler, name = "IODispatcher")

    val Default: CoroutineDispatcher =
        StandardTestDispatcher(scheduler, name = "DefaultDispatcher")

    val Unconfined: CoroutineDispatcher =
        UnconfinedTestDispatcher(scheduler, name = "UnconfinedDispatcher")
}