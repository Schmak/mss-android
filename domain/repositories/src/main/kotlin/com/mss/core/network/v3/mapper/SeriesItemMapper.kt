package com.mss.core.network.v3.mapper

import com.mss.core.domain.SeriesItem
import com.mss.core.network.v3.mapper.common.LocalDateMapper
import com.mss.core.network.v3.model.SeriesItemDto
import com.mss.core.utils.Mapper

internal object SeriesItemMapper : Mapper<SeriesItemDto, SeriesItem> {
    val pageMapper = PageMapper(SeriesItemMapper)

    override fun SeriesItemDto.map() =
        SeriesItem(
            slug = uuid,
            name = name,
            picture = picture,
            region = region,
            lastEvent = lastEventDate?.let { date ->
                SeriesItem.LastEvent(
                    name = lastEvent.name,
                    date = date.let(LocalDateMapper),
                )
            }
        )
}