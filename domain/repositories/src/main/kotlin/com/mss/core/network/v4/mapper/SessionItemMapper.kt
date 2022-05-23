package com.mss.core.network.v4.mapper

import com.mss.core.domain.SessionItem
import com.mss.core.network.v4.mapper.ref.EventReferenceMapper
import com.mss.core.network.v4.mapper.ref.SeriesReferenceMapper
import com.mss.core.network.v4.model.SessionItemDto
import com.mss.core.utils.Mapper
import com.mss.core.utils.TimestampConverter

object SessionItemMapper : Mapper<SessionItemDto, SessionItem> {
    val pageMapper = PageMapper(SessionItemMapper)

    override fun SessionItemDto.map() = SessionItem(
        slug = session.slug,
        name = session.name,
        event = event.let(EventReferenceMapper),
        series = series.let(SeriesReferenceMapper),
        startTime = TimestampConverter.convert(startTime, startTimeUtc),
    )
}