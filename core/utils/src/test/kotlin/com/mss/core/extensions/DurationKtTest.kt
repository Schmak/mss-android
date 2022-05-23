package com.mss.core.extensions

import com.mss.core.test.junit5.AbstractTestCaseWithOrigin
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

internal class DurationKtTest {
    @ParameterizedTest
    @MethodSource("cases")
    fun `format as {hh}-mm-ss`(case: TestCase) {
        val actual = case.duration.formatAsHHMMSS()
        Assertions.assertThat(actual).isEqualTo(case.expected)
    }

    private fun cases() = listOf(
        TestCase(Duration.ZERO, "00:00"),
        TestCase(1.seconds, "00:01"),
        TestCase(59.seconds, "00:59"),
        TestCase(1.minutes, "01:00"),
        TestCase(99.seconds, "01:39"),
        TestCase(10.minutes, "10:00"),
        TestCase(625.seconds, "10:25"),
        TestCase(59.minutes + 58.seconds, "59:58"),
        TestCase(1.hours, "1:00:00"),
        TestCase(1.hours + 2.minutes, "1:02:00"),
        TestCase(1.hours + 2.minutes + 3.seconds, "1:02:03"),
        TestCase(49.hours + 58.minutes + 37.seconds, "49:58:37"),
    )

    data class TestCase(
        val duration: Duration,
        val expected: String,
    ) : AbstractTestCaseWithOrigin()
}