package com.mss.core.domain

import com.mss.core.domain.ref.EventReference
import com.mss.core.domain.ref.SeriesReference
import com.mss.core.domain.ref.create
import com.mss.core.test.utils.DEFAULT_OFFSET_DATE_TIME
import java.time.OffsetDateTime

fun SessionItem.Companion.create(
    name: String = "session name",
    slug: String = "session slug",
    event: EventReference = EventReference.create(),
    series: SeriesReference = SeriesReference.create(),
    startTime: OffsetDateTime? = DEFAULT_OFFSET_DATE_TIME,
) = SessionItem(
    name = name,
    slug = slug,
    event = event,
    series = series,
    startTime = startTime
)