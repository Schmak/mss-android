package com.mss.core.test.annotation

import com.mss.core.test.junit5.extension.CoroutineExtension
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Tag("coroutine")
@ExtendWith(CoroutineExtension::class)
@Execution(ExecutionMode.SAME_THREAD)
annotation class CoroutineTest