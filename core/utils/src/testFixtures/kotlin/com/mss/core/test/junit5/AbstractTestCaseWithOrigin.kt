package com.mss.core.test.junit5

abstract class AbstractTestCaseWithOrigin : ITestCaseWithOrigin {
    final override val constructedAt: String =
        Thread.currentThread()
            .stackTrace
            .drop(1)
            .first {
                val caller = Class.forName(it.className)
                !ITestCaseWithOrigin::class.java.isAssignableFrom(caller)
            }.let {
                "${it.className}(${it.fileName}:${it.lineNumber})"
            }
}