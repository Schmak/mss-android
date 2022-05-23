package com.mss.core.network.v4.model

import com.mss.core.network.v4.model.ref.EventReferenceDto
import com.mss.core.network.v4.model.ref.SeriesReferenceDto
import com.mss.core.network.v4.model.ref.SessionReferenceDto
import com.mss.core.network.v4.model.ref.create

fun SessionItemDto.Companion.create(
    session: SessionReferenceDto = SessionReferenceDto.create(),
    event: EventReferenceDto = EventReferenceDto.create(),
    series: SeriesReferenceDto = SeriesReferenceDto.create(),
    status: String = "Active",
    startTime: Long? = 1234,
    startTimeUtc: Long? = 2345,
) = SessionItemDto(
    session = session,
    event = event,
    series = series,
    status = status,
    startTime = startTime,
    startTimeUtc = startTimeUtc
)