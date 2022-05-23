package com.mss.core.utils

import com.mss.core.test.junit5.AbstractTestCaseWithOrigin
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneOffset

internal class TimestampConverterTest {
    @ParameterizedTest
    @MethodSource("cases")
    fun convert(case: TestCase) {
        val actual = TimestampConverter.convert(
            timestamp = case.timestamp,
            utcTimestamp = case.utcTimestamp
        )
        assertThat(actual).isEqualTo(case.expected)
    }

    private fun cases() = listOf(
        TestCase(
            timestamp = null,
            utcTimestamp = null,
            expected = null,
        ),
        TestCase(
            timestamp = 1_653_236_100,
            utcTimestamp = null,
            expected = LocalDate.of(2022, 5, 22).atTime(16, 15).atOffset(ZoneOffset.UTC),
        ),
        TestCase(
            timestamp = null,
            utcTimestamp = 1_653_236_100,
            expected = LocalDate.of(2022, 5, 22).atTime(16, 15).atOffset(ZoneOffset.UTC),
        ),
        TestCase(
            timestamp = 1_653_236_100,
            utcTimestamp = 1_653_236_100,
            expected = LocalDate.of(2022, 5, 22).atTime(16, 15).atOffset(ZoneOffset.UTC),
        ),
        TestCase(
            timestamp = 1_653_236_100,
            utcTimestamp = 1_653_254_100,
            expected = LocalDate.of(2022, 5, 22).atTime(16, 15).atOffset(ZoneOffset.ofHours(-5))
        ),
        TestCase(
            timestamp = 1_653_231_600,
            utcTimestamp = 1_653_224_400,
            expected = LocalDate.of(2022, 5, 22).atTime(15, 0).atOffset(ZoneOffset.ofHours(2))
        ),
    )

    data class TestCase(
        val timestamp: Long?,
        val utcTimestamp: Long?,
        val expected: OffsetDateTime?
    ) : AbstractTestCaseWithOrigin()
}