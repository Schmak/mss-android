package com.mss.annotation

import com.mss.junit5.extension.AssertJConfigurationExtension
import com.mss.junit5.extension.ClearMockExtension
import com.mss.junit5.extension.PrintTestCaseOriginExtension
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.extension.ExtendWith

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Tag("integration")
@ExtendWith(
    PrintTestCaseOriginExtension::class,
    AssertJConfigurationExtension::class,
    ClearMockExtension::class
)
annotation class IntegrationTest