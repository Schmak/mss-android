@file:Suppress("MagicNumber")

package com.mss.core.test.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset

val DEFAULT_LOCAL_DATE: LocalDate =
    LocalDate.ofYearDay(2020, 1)

val DEFAULT_LOCAL_DATE_TIME: LocalDateTime =
    DEFAULT_LOCAL_DATE.atStartOfDay()

val DEFAULT_OFFSET_DATE_TIME: OffsetDateTime =
    DEFAULT_LOCAL_DATE_TIME.atOffset(ZoneOffset.UTC)