package com.mss.core.domain.mapper

import com.mss.core.domain.SeriesItem
import com.mss.core.utils.Mapper
import com.mss.network.model.SeriesItemDto
import java.time.LocalDate
import kotlin.time.Duration.Companion.seconds

internal object SeriesItemMapper : Mapper<SeriesItemDto, SeriesItem> {
    val pageMapper = PageMapper(SeriesItemMapper)

    override fun SeriesItemDto.map() =
        SeriesItem(
            name = name,
            picture = picture,
            region = region,
            lastEvent = lastEventDate?.let { date ->
                SeriesItem.LastEvent(
                    name = lastEvent.name,
                    date = LocalDate.ofEpochDay(date.seconds.inWholeDays),
                )
            }
        )
}