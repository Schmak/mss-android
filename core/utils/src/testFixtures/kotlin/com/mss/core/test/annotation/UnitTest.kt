package com.mss.core.test.annotation

import com.mss.core.test.junit5.extension.AssertJConfigurationExtension
import com.mss.core.test.junit5.extension.ClearMockExtension
import com.mss.core.test.junit5.extension.PrintTestCaseOriginExtension
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Tag("unit")
@ExtendWith(PrintTestCaseOriginExtension::class, AssertJConfigurationExtension::class, ClearMockExtension::class)
annotation class UnitTest