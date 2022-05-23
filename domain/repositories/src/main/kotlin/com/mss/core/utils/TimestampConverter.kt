package com.mss.core.utils

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

object TimestampConverter {

    fun convert(timestamp: Long?, utcTimestamp: Long?): OffsetDateTime? {
        if (timestamp == null)
            return if (utcTimestamp == null)
                null
            else
                Instant.ofEpochSecond(utcTimestamp).atOffset(ZoneOffset.UTC)
        if (utcTimestamp == null)
            return Instant.ofEpochSecond(timestamp).atOffset(ZoneOffset.UTC)

        val offset = ZoneOffset.ofTotalSeconds((timestamp - utcTimestamp).toInt())
        return Instant.ofEpochSecond(utcTimestamp).atOffset(offset)
    }
}