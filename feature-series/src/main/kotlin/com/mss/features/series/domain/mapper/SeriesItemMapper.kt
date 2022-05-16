package com.mss.features.series.domain.mapper

import com.mss.core.utils.Mapper
import com.mss.domain.SeriesItem
import com.mss.network.model.SeriesItemDto
import java.time.LocalDate
import kotlin.time.Duration.Companion.seconds

internal object SeriesItemMapper : Mapper<SeriesItemDto, SeriesItem> {
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